package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day01Test {

    @Test
    public void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2020, 1, 1));
        assertEquals(514579, problem.solve1());
        assertEquals(241861950, problem.solve2());
    }

}