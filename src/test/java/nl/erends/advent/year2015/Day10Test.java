package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readLine(2015, 10, 1));
        problem.setTarget1(4);
        problem.setTarget2(6);
        assertEquals(6, problem.solve1().intValue());
        assertEquals(8, problem.solve2().intValue());
    }
}
