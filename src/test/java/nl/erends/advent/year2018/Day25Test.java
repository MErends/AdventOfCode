package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    @Test
    void day24Test1() {
        Day25 problem = new Day25();
        problem.setInput(Util.readInput(2018, 25, 1));
        assertEquals(2, problem.solve1().intValue());
    }
}