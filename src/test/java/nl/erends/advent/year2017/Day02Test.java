package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2017, 2, 1));
        assertEquals(18, problem.solve1().intValue());
    }

    @Test
    public void day02Test2() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2017, 2, 2));
        assertEquals(9, problem.solve2().intValue());
    }
}