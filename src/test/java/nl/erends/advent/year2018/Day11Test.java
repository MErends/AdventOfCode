package nl.erends.advent.year2018;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(18);
        assertEquals("33,45", problem.solve1());
        assertEquals("90,269,16", problem.solve2());
    }
}