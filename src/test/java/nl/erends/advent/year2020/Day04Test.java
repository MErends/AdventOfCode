package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day04Test {
    
    @Test
    public void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2020, 4, 1));
        assertEquals(2, problem.solve1());
    }
    
    @Test
    public void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2020, 4, 2));
        assertEquals(4, problem.solve2());
    }
}
