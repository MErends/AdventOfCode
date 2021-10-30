package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day09Test {
    
    @Test
    void day09Test() {
        Day09 problem = new Day09();
        problem.setInput(Util.readInput(2020, 9, 1));
        problem.setTestPreamble();
        assertEquals(127, problem.solve1());
        assertEquals(62, problem.solve2());
    }
}
