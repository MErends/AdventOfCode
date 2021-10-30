package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 1));
        assertEquals(2, problem.solve1().intValue());
    }
    
    @Test
    void day05Test2() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 2));
        assertEquals(2, problem.solve2().intValue());
    }
}
