package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2018, 1, 1));
        assertEquals(3, problem.solve1().intValue());
        assertEquals(2, problem.solve2().intValue());
    }
}