package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day08Test {

    @Test
    public void day08Test() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2020, 8, 1));
        assertEquals(5, problem.solve1());
        assertEquals(8, problem.solve2());
    }
}
