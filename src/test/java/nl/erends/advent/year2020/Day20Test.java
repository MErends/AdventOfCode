package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day20Test {
    
    @Test
    public void day20Test() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2020, 20, 1));
        assertEquals(20899048083289L, problem.solve1());
        assertEquals(273, problem.solve2());
    }
}
