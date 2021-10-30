package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    @Test
    void day25Test1() {
        assertEquals(3099, new Day25().solve1().intValue());
    }
}