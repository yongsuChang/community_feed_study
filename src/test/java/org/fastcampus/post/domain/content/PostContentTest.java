package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String text = "This is a test content.";

        // when
        PostContent content = new PostContent(text);

        // then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowException() {
        // given
        String content = "a".repeat(501);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슳"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowException(String koreanWord) {
        // given
        String content = koreanWord.repeat(501);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        // given
        String content = "a".repeat(4);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContent_whenUpdated_thenReturnUpdatedContent() {
        // given
        String initialContent = "Initial content.";
        String updatedContent = "Updated content.";
        PostContent content = new PostContent(initialContent);

        // when
        content.updateContent(updatedContent);

        // then
        assertEquals(updatedContent, content.getContentText());
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        // given
        String initialContent = "Initial content.";
        PostContent content = new PostContent(initialContent);

        // when
        String updatedContent = "Updated content.";
        content.updateContent(updatedContent);

        // then
        assertEquals(updatedContent, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        // given
        String initialContent = "Initial content.";
        PostContent content = new PostContent(initialContent);

        // when & then
        String value = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슳"})
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord) {
        // given
        String initialContent = "Initial content.";
        PostContent content = new PostContent(initialContent);

        // when & then
        String value = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenThrowError() {
        // given
        String initialContent = "Initial content.";
        PostContent content = new PostContent(initialContent);

        // when & then
        String value = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }
}
