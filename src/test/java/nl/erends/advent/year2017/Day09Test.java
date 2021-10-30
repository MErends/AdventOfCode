package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("{<a>,<a>,<a>,<a>}");
        assertEquals(1, problem.solve1().intValue());
    }

    @Test
    void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput("<{o\"i!a,<{i<a>");
        assertEquals(10, problem.solve2().intValue());
    }
}