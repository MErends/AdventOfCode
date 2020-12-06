package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day06Test {
    
    @Test
    public void day06Test() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2020, 6, 1));
        assertEquals(11, problem.solve1());
        assertEquals(6, problem.solve2());
    }
}
