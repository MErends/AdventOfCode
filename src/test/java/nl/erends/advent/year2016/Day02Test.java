package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2016, 2, 1));
        assertEquals("1985", problem.solve1());
        assertEquals("5DB3", problem.solve2());
    }
}
