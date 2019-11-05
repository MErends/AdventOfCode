package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03Test {

    @Test
    public void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 1));
        assertEquals(2, problem.solve1().intValue());
    }

    @Test
    public void day03Test2() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 2));
        assertEquals(4, problem.solve1().intValue());
        assertEquals(3, problem.solve2().intValue());
    }

    @Test
    public void day03Test3() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 3));
        assertEquals(2, problem.solve1().intValue());
        assertEquals(11, problem.solve2().intValue());
    }

    @Test
    public void day03Test4() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 4));
        assertEquals(3, problem.solve2().intValue());
    }
}