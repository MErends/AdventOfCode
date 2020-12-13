package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day13Test {

    @Test
    public void day13Test() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2020, 13, 1));
        assertEquals(295, problem.solve1());
        assertEquals(1068781, problem.solve2());
    }
}
