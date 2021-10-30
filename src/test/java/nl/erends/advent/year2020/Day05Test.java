package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day05Test {
    
    @Test
    void day05Test() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2020, 5, 1));
        assertEquals(820, problem.solve1());
    }
}
