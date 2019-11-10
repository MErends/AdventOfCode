package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    @Test
    public void day23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2016, 23, 1));
        assertEquals(3, problem.solve1().intValue());
    }
}
