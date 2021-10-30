package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day17Test {

    @Test
    void day17Test() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2020, 17, 1));
        assertEquals(112, problem.solve1());
        assertEquals(848, problem.solve2());
    }
}
