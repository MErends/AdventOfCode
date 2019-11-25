package nl.erends.advent.year2018;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {

    @Test
    public void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput("dabAcCaCBAcCcaDA");
        assertEquals(10, problem.solve1().intValue());
        assertEquals(4, problem.solve2().intValue());
    }    
}