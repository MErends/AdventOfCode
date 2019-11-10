package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2016, 15, 1));
        assertEquals(5, problem.solve1().intValue());
        assertEquals(35, problem.solve2().intValue());
    }
}
