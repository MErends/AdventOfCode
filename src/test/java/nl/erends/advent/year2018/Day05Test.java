package nl.erends.advent.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput("dabAcCaCBAcCcaDA");
        assertEquals(10, problem.solve1().intValue());
        assertEquals(4, problem.solve2().intValue());
    }    
}