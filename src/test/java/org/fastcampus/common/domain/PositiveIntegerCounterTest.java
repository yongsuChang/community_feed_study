package org.fastcampus.common.domain;

import org.junit.jupiter.api.Test;

public class PositiveIntegerCounterTest {


    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        // then
        assert counter.getCount() == 1;
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // when
        counter.decrease();

        // then
        assert counter.getCount() == 0;
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assert counter.getCount() == 0;
    }
}
