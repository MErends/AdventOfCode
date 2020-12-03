package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day03Test {
    
    @Test
    public void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2020, 3, 1));
        assertEquals(7, problem.solve1());
        assertEquals(336, problem.solve2());
    }
}
