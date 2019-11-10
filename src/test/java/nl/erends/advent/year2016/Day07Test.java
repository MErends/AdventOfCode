package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {

    @Test
    public void day07Test1() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2016, 7, 1));
        assertEquals(2, problem.solve1().intValue());
        assertEquals(3, problem.solve2().intValue());
    }
}
