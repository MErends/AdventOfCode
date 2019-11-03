package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void day10Test1() {
        String input = Util.readLine(2015, 10, 1);
        Day10 problem = new Day10();
        problem.setTarget1(4);
        assertEquals(6, problem.solve1(input).intValue());
        problem = new Day10();
        problem.setTarget1(4);
        problem.setTarget2(6);
        assertEquals(8, problem.solve2(input).intValue());
    }
}
