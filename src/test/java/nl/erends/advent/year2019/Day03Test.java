package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2019, 3, 1));
        assertEquals(159, problem.solve1().intValue());
        assertEquals(610, problem.solve2().intValue());
    }
}