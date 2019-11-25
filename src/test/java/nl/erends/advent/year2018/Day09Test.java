package nl.erends.advent.year2018;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09Test {

    @Test
    public void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("10 players; last marble is worth 1618 points");
        assertEquals(8317, problem.solve1().longValue());
        assertEquals(74765078, problem.solve2().longValue());
    }
}