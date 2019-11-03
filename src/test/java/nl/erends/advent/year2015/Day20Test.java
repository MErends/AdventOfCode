package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test {

    @Test
    public void day20Test1() {
        int input = Integer.parseInt(Util.readLine(2015, 20, 1));
        Day20 problem = new Day20();
        assertEquals(96, problem.solve1(input).intValue());
        assertEquals(84, problem.solve2(input).intValue());
    }
}
