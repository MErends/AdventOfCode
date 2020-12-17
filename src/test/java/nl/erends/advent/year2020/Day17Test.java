package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day17Test {

    @Test
    public void day17Test() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2020, 17, 1));
        assertEquals(112, problem.solve1());
        assertEquals(848, problem.solve2());
    }
}
