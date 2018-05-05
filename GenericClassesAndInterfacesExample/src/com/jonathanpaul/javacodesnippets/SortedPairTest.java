package com.jonathanpaul.javacodesnippets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedPairTest {

    @Test
    public void shouldRetainOrderOfPair() {
        // Integers are comparable with themselves
        SortedPair<Integer> pair = new SortedPair<>(1, 2);

        assertEquals(1, pair.getFirst().intValue());
        assertEquals(2, pair.getSecond().intValue());
    }

    @Test
    public void shouldFlipOrderOfPair() {
        SortedPair<Integer> pair = new SortedPair<>(2, 1);

        assertEquals(1, pair.getFirst().intValue());
        assertEquals(2, pair.getSecond().intValue());
    }
}
