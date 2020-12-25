package nl.erends.advent.year2020;

import org.junit.Test;

import java.util.Arrays;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day25Test {

    @Test
    public void day25Test() {
        Day25 problem = new Day25();
        problem.setInput(Arrays.asList("5764801", "17807724"));
        assertEquals(14897079, problem.solve1());
    }
}
