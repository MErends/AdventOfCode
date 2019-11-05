package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2015, 11, 1));
        assertEquals("abcdffaa", problem.solve1());
    }

    @Test
    public void day11Test2() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2015, 11, 2));
        assertEquals("ghjbbcdd", problem.solve2());
    }
}
