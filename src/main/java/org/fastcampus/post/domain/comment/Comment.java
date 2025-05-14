package org.fastcampus.post.domain.comment;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null.");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null.");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if(user == null || user.equals(this.author)) {
            throw new IllegalArgumentException("User cannot be null and cannot like their own post.");
        }
        likeCount.increase();
    }

    public void unlike(User user) {
        if(user == null || user.equals(this.author)) {
            throw new IllegalArgumentException("User cannot be null and cannot unlike their own post.");
        }
        likeCount.decrease();
    }

    public void updateComment(User user, String updateContent) {
        if(user == null || !user.equals(this.author)) {
            throw new IllegalArgumentException("User cannot be null and must be the author to update the comment.");
        }
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
