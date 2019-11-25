package nl.erends.advent.year2018;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {

    @Test
    public void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        assertEquals(138, problem.solve1().intValue());
        assertEquals(66, problem.solve2().intValue());
    }
}