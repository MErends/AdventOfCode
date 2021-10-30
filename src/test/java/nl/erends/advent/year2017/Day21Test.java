package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2017, 21, 1));
        assertEquals(142, problem.solve1().intValue());
        assertEquals(1879071, problem.solve2().intValue());
    }
}