package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository,
        LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Post ID cannot be null.");
        }
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + id));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + dto.postId()));
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = postRepository.findById(dto.targetId())
            .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + dto.targetId()));
        User user = userService.getUser(dto.userId());

        // 이미 좋아요를 눌렀다면 반환
        if(likeRepository.checkLike(post, user)) {
            return;
        }
        post.like(user);
        postRepository.save(post);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = postRepository.findById(dto.targetId())
            .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + dto.targetId()));
        User user = userService.getUser(dto.userId());

        // 좋아요를 누르지 않았다면 반환
        if(!likeRepository.checkLike(post, user)) {
            return;
        }
        post.unlike(user);
        postRepository.save(post);
    }
}
