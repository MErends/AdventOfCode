package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {
    
    @Test
    void day22Test1() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2016, 22, 1));
        assertEquals(1020, problem.solve1().intValue());
        problem = new Day22();
        problem.setInput(Util.readInput(2016, 22, 1));
        assertEquals(198, problem.solve2().intValue());
    }
}