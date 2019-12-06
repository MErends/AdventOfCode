package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test {

    @Test
    public void day06Test1() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2019, 6, 1));
        assertEquals(42, problem.solve1().intValue());
    }

    @Test
    public void day06Test2() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2019, 6, 2));
        assertEquals(4, problem.solve2().intValue());
    }
}
