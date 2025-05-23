package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String contentText, PostPublicationState state) {
        return new Post(id, author, new PostContent(contentText), state);
    }

    public static Post createDefaultPost(Long id, User author, String contentText) {
        return new Post(id, author, new PostContent(contentText), PostPublicationState.PUBLIC);
    }

    protected Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    protected Post(Long id, User author, Content content, PostPublicationState state) {
        if(author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
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

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if(user == null || !user.equals(this.author)) {
            throw new IllegalArgumentException("User cannot be null and must be the author to update the post.");
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContentObject() {
        return content;
    }
}
