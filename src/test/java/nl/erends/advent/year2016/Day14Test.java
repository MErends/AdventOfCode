package nl.erends.advent.year2016;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput("abc");
        assertEquals(22728, problem.solve1().intValue());
    }
}
