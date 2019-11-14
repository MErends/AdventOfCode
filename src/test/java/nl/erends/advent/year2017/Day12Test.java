package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Test {
    
    @Test
    public void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2017, 12, 1));
        assertEquals(6, problem.solve1().intValue());
        assertEquals(2, problem.solve2().intValue());
    }
}