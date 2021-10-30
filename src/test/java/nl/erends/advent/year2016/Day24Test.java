package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    @Test
    void day24Test1() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2016, 24, 1));
        assertEquals(14, problem.solve1().intValue());
        assertEquals(20, problem.solve2().intValue());
    }
}
