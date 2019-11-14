package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    @Test
    public void dat23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2017, 23, 1));
        assertEquals(5929, problem.solve1().intValue());
        assertEquals(907, problem.solve2().intValue());
    }
}