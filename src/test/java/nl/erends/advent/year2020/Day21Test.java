package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    @Test
    void day21Test() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2020, 21, 1));
        assertEquals("5", problem.solve1());
        assertEquals("mxmxvkd,sqjhc,fvjkl", problem.solve2());
    }
}
