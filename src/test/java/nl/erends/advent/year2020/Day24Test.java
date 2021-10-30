package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day24Test {
    
    @Test
    void day24Test() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2020, 24, 1));
        assertEquals(10, problem.solve1());
        assertEquals(2208, problem.solve2());
    }
}
