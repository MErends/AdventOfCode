package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19Test {

    @Test
    public void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2017, 19, 1));
        assertEquals("SXPZDFJNRL", problem.solve1());
        assertEquals("18126", problem.solve2());
    }
}