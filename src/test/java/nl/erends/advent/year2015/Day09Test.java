package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {

    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput(Util.readInput(2015, 9, 1));
        assertEquals(605, problem.solve1().intValue());
        assertEquals(982, problem.solve2().intValue());
    }
}
