package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2018, 13, 1));
        assertEquals("7,3", problem.solve1());
    }

    @Test
    void day13Test2() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2018, 13, 2));
        assertEquals("6,4", problem.solve2());
    }
}