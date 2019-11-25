package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2018, 15, 1));
        assertEquals(27730, problem.solve1().intValue());
        assertEquals(4988, problem.solve2().intValue());
    }
}