package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2018, 17, 1));
        assertEquals(57, problem.solve1().intValue());
        assertEquals(29, problem.solve2().intValue());
    }
}