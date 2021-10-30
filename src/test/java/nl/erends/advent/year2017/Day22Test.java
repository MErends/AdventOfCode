package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    @Test
    void day22Test1() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2017, 22, 1));
        assertEquals(5280, problem.solve1().intValue());
        assertEquals(2512261, problem.solve2().intValue());
    }
}