package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UserInfoTest {

    @Test
    void givenNameAndProfileImageUrl_whenCreated_thenNoException() {
        // given
        String name = "John Doe";
        String profileImageUrl = "http://example.com/profile.jpg";

        // when
        // then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenEmptyName_whenCreated_thenIllegalArgumentException() {
        // given
        String name = "";
        String profileImageUrl = "http://example.com/profile.jpg";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenNullName_whenCreated_thenIllegalArgumentException() {
        // given
        String name = null;
        String profileImageUrl = "http://example.com/profile.jpg";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }
}
