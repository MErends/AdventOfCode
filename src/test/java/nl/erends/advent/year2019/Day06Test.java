package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void day06Test1() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2019, 6, 1));
        assertEquals(42, problem.solve1().intValue());
    }

    @Test
    void day06Test2() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2019, 6, 2));
        assertEquals(4, problem.solve2().intValue());
    }
}
