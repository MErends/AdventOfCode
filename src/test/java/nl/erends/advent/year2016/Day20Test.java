package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test {

    @Test
    public void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2016, 20, 1));
        problem.setMaxValue(9);
        assertEquals(3, problem.solve1().intValue());
        assertEquals(2, problem.solve2().intValue());
    }
}
