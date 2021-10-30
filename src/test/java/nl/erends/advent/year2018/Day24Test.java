package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    @Test
    void day24Test1() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2018, 24, 1));
        assertEquals(5216, problem.solve1().intValue());
        assertEquals(51, problem.solve2().intValue());
    }
}