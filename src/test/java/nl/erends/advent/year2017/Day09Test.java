package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09Test {
    
    @Test
    public void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("{<a>,<a>,<a>,<a>}");
        assertEquals(1, problem.solve1().intValue());
    }

    @Test
    public void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput("<{o\"i!a,<{i<a>");
        assertEquals(10, problem.solve2().intValue());
    }
}