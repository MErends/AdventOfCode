package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day20Test {

    @Test
    public void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2019, 20 , 1));
        assertEquals(23, problem.solve1());
    }

    @Test
    public void day20Test2() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2019, 20 , 2));
        assertEquals(396, problem.solve2());
    }

}