package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput("65,8921");
        assertEquals(588, problem.solve1().intValue());
        assertEquals(309, problem.solve2().intValue());
    }
}