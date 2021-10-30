package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2015, 8, 1));
        assertEquals(12, problem.solve1().intValue());
        assertEquals(19, problem.solve2().intValue());
    }
}
