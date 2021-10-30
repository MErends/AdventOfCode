package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day19Test {
    
    @Test
    void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2020, 19, 1));
        assertEquals(2, problem.solve1());
    }
    
    @Test
    void day19Test2() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2020, 19, 2));
        assertEquals(3, problem.solve1());
        assertEquals(12, problem.solve2());
    }
}
