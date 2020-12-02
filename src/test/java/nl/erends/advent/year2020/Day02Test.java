package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day02Test {
    
    @Test
    public void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2020, 2, 1));
        assertEquals(2, problem.solve1());
        assertEquals(1, problem.solve2());
    }
}
