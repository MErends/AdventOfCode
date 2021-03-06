package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 1));
        assertEquals(5, problem.solve1().intValue());
    }

    @Test
    public void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 2));
        assertEquals(2, problem.solve1().intValue());
    }

    @Test
    public void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 3));
        assertEquals(12, problem.solve1().intValue());
    }

    @Test
    public void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 4));
        assertEquals(4, problem.solve2().intValue());
    }

}
