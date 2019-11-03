package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void day11Test1() {
        String input = Util.readLine(2015, 11, 1);
        Day11 problem = new Day11();
        assertEquals("abcdffaa", problem.solve1(input));
    }

    @Test
    public void day11Test2() {
        String input = Util.readLine(2015, 11, 2);
        Day11 problem = new Day11();
        assertEquals("ghjbbcdd", problem.solve2(input));
    }
}
