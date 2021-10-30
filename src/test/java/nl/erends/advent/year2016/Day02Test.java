package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2016, 2, 1));
        assertEquals("1985", problem.solve1());
        assertEquals("5DB3", problem.solve2());
    }
}
