package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18Test {

    @Test
    public void day18Test1() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2017, 18, 1));
        assertEquals(4, problem.solve1().intValue());
    }

    @Test
    public void day18Test2() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2017, 18, 2));
        assertEquals(3, problem.solve2().intValue());
    }
}