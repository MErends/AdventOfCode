package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {

    @Test
    public void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2018, 13, 1));
        assertEquals("7,3", problem.solve1());
    }

    @Test
    public void day13Test2() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2018, 13, 2));
        assertEquals("6,4", problem.solve2());
    }
}