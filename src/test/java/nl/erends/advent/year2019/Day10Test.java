package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day10Test {

    @Test
    public void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2019, 10, 1));
        assertEquals(210, problem.solve1());
        assertEquals(802, problem.solve2());
    }
}