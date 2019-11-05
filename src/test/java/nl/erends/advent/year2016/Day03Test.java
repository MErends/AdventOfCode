package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03Test {

    @Test
    public void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2016, 3, 1));
        assertEquals(3, problem.solve1().intValue());
        assertEquals(6, problem.solve2().intValue());
    }
}
