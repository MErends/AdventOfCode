package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void day06Test1() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2018, 6, 1));
        assertEquals(17, problem.solve1().intValue());
        assertEquals(90, problem.solve2().intValue());
    }
}