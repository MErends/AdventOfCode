package nl.erends.advent.year2020;

import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day23Test {

    @Test
    public void day23Test() {
        Day23 problem = new Day23();
        problem.setInput("389125467");
        assertEquals(67384529, problem.solve1());
        assertEquals(149245887792L, problem.solve2());
    }
}
