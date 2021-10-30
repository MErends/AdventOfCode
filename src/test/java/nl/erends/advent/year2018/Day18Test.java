package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void day18Test1() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2018, 18, 1));
        assertEquals(1147, problem.solve1().intValue());
        assertEquals(0, problem.solve2().intValue());
    }
}