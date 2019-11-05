package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2015, 15, 1));
        assertEquals(62842880, problem.solve1().intValue());
        assertEquals(57600000, problem.solve2().intValue());
    }
}
