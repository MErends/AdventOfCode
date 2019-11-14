package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {
    
    @Test
    public void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2017, 13, 1));
        assertEquals(24, problem.solve1().intValue());
        assertEquals(10, problem.solve2().intValue());
    }
}