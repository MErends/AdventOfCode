package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    
    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2016, 10, 1));
        problem.setLowCheck(2);
        problem.setHighCheck(5);
        assertEquals(2, problem.solve1().intValue());
        assertEquals(30, problem.solve2().intValue());
    }

}
