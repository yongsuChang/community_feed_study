package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class CommentService {

    private final UserService userService;
    private final PostService postService;

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService,
        CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null.");
        }
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + id));
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User author = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(null, post, author, dto.content());

        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = commentRepository.findById(dto.commentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + dto.commentId()));
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = commentRepository.findById(dto.targetId())
            .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + dto.targetId()));
        User user = userService.getUser(dto.userId());

        // 이미 좋아요를 눌렀다면 반환
        if(likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.like(user);
        commentRepository.save(comment);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = commentRepository.findById(dto.targetId())
            .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + dto.targetId()));
        User user = userService.getUser(dto.userId());

        // 이미 좋아요를 눌렀다면 반환
        if(!likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.unlike(user);
        commentRepository.save(comment);
    }
}
