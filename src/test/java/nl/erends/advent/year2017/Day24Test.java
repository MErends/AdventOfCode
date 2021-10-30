package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    @Test
    void dat24Test1() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2017, 24, 1));
        assertEquals(31, problem.solve1().intValue());
        assertEquals(19, problem.solve2().intValue());
    }
}