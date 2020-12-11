package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day11Test {

    @Test
    public void deay11Test() {
        Day11 problem = new Day11();
        problem.setInput(Util.readInput(2020, 11, 1));
        assertEquals(37, problem.solve1());
        assertEquals(26, problem.solve2());
    }
}
