package nl.erends.advent.year2016;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19Test {

    @Test
    public void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(5);
        assertEquals(3, problem.solve1().intValue());
        assertEquals(2, problem.solve2().intValue());
    }
}
