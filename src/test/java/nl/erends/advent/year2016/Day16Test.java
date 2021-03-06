package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void day15Test1() {
        Day16 problem = new Day16();
        problem.setInput("11100010111110100");
        assertEquals("10100011010101011", problem.solve1());
        assertEquals("01010001101011001", problem.solve2());
    }
}
