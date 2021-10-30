package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2017, 2, 1));
        assertEquals(18, problem.solve1().intValue());
    }

    @Test
    void day02Test2() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2017, 2, 2));
        assertEquals(9, problem.solve2().intValue());
    }
}