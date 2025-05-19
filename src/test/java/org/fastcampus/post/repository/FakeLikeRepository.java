package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

public class FakeLikeRepository implements LikeRepository {
    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment, Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLikes.get(post) == null) {
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null) {
            users = Set.of(user);
        } else {
            users.add(user);
        }
        postLikes.put(post, users);
    }

    @Override
    public void unlike(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null) {
            return;
        }
        users.remove(user);
        postLikes.put(post, users);
    }

    @Override
    public boolean checkLike(Comment post, User user) {
        if(commentLikes.get(post) == null) {
            return false;
        }
        return commentLikes.get(post).contains(user);
    }

    @Override
    public void like(Comment post, User user) {
        Set<User> users = commentLikes.get(post);
        if(users == null) {
            users = Set.of(user);
        } else {
            users.add(user);
        }
        commentLikes.put(post, users);
    }

    @Override
    public void unlike(Comment post, User user) {
        Set<User> users = commentLikes.get(post);
        if(users == null) {
            return;
        }
        users.remove(user);
        commentLikes.put(post, users);
    }
}
