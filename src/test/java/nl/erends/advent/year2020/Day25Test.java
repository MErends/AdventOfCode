package nl.erends.advent.year2020;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static nl.erends.advent.util.Assert.assertEquals;

class Day25Test {

    @Test
    void day25Test() {
        Day25 problem = new Day25();
        problem.setInput(Arrays.asList("5764801", "17807724"));
        assertEquals(14897079, problem.solve1());
    }
}
