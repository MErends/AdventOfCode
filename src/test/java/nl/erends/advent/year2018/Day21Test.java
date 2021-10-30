package nl.erends.advent.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {
    
    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        assertEquals(212115, problem.solve1().intValue());
        assertEquals(9258470, problem.solve2().intValue());
    }
}