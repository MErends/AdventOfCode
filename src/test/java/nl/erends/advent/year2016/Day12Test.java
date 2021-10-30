package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2016, 12, 1));
        assertEquals(43, problem.solve1().intValue());
        assertEquals(42, problem.solve2().intValue());
    }
}
