package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day14Test {

    @Test
    public void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(Util.readInput(2020, 14, 1));
        assertEquals(165, problem.solve1());
    }

    @Test
    public void day14Test2() {
        Day14 problem = new Day14();
        problem.setInput(Util.readInput(2020, 14, 2));
        assertEquals(208, problem.solve2());
    }
}
