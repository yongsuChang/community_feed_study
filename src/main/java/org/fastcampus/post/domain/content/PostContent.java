package org.fastcampus.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if(contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        if(contentText.length() < MIN_POST_LENGTH || contentText.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("Content length must be between "
                + MIN_POST_LENGTH
                + " and "
                + MAX_POST_LENGTH
                + " characters.");
        }
    }
}
