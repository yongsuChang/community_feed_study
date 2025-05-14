package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
        // given
        String content = "This is a comment.";

        // when
        CommentContent commentContent = new CommentContent(content);

        // then
        assertEquals(content, commentContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowException() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슳"})
    void givenContentLengthIsOverAndKorean_whenCreateCommentContent_thenThrowException(String koreanWord) {
        // given
        String content = koreanWord.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsNullOrEmpty_whenCreateCommentContent_thenThrowException(String content) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }
}
