package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {
    
    @Test
    public void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2017, 4, 1));
        assertEquals(2, problem.solve1().intValue());
    }

    @Test
    public void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2017, 4, 2));
        assertEquals(3, problem.solve2().intValue());
    }
}