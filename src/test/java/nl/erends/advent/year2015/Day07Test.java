package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {

    @Test
    public void day07Test1() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2015, 7, 1));
        assertEquals(492, problem.solve1().intValue());
        assertEquals(65043, problem.solve2().intValue());
    }
}
