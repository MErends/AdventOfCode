package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Test {

    @Test
    public void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2018, 12, 1));
        assertEquals(325L, problem.solve1().longValue());
        assertEquals(999999999374L , problem.solve2().longValue());
    }
}