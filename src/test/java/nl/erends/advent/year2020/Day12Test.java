package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day12Test {
    
    @Test
    void day12Test() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2020, 12, 1));
        assertEquals(25, problem.solve1());
        assertEquals(286, problem.solve2());
    }
}
