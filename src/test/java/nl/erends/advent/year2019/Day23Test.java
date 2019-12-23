package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day23Test {

    @Test
    public void day23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readLine(2019, 23, 1));
        assertEquals(24602, problem.solve1());
        assertEquals(19641, problem.solve2());
    }
}