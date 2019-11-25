package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    @Test
    public void day23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2018, 23, 1));
        assertEquals(7, problem.solve1().intValue());
    }

    @Test
    public void day23Test2() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2018, 23, 2));
        assertEquals(36, problem.solve2().intValue());
    }
}