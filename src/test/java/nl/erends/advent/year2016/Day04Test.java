package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2016, 4, 1));
        assertEquals(1996, problem.solve1().intValue());
        assertEquals(482, problem.solve2().intValue());
    }
}
