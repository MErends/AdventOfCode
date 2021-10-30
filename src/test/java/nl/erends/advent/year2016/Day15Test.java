package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2016, 15, 1));
        assertEquals(5, problem.solve1().intValue());
        assertEquals(35, problem.solve2().intValue());
    }
}
