package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2018, 16, 1));
        assertEquals(642, problem.solve1().intValue());
        problem = new Day16();
        problem.setInput(Util.readInput(2018, 16, 1));
        assertEquals(481, problem.solve2().intValue());
    }
}