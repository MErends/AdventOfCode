package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {
    
    @Test
    public void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2017, 11, 1));
        assertEquals(696, problem.solve1().intValue());
        assertEquals(1461, problem.solve2().intValue());
    }
}