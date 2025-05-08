package org.fastcampus.post.domain.content;

public class CommentContent extends Content{

    private static final int MAX_COMMENT_LENGTH = 200;

    public CommentContent(String content) {
        super(content);
    }
    @Override
    protected void checkText(String contentText) {
        if(contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        if(contentText.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("Content length must be less than "
                + MAX_COMMENT_LENGTH
                + " characters.");
        }
    }
}
