package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2020, 10 , 1));
        assertEquals(7 * 5, problem.solve1());
        assertEquals(8, problem.solve2());
    }

    @Test
    void day10Test2() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2020, 10 , 2));
        assertEquals(22 * 10, problem.solve1());
        assertEquals(19208, problem.solve2());
    }
}
