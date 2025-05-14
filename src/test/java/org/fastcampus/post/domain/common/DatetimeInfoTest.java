package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {
    @Test
    void givenCreated_whenUpdated_thenTimeAndStateAreUpdated() {
        // given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime initialDateTime = datetimeInfo.getDateTime();

        // when
        datetimeInfo.updateEditDateTime();

        // then
        assertTrue(datetimeInfo.isEdited());
        assertNotEquals(initialDateTime, datetimeInfo.getDateTime());
    }

}
