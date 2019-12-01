package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2019, 1, 1));
        assertEquals(34241, problem.solve1().intValue());
    }
    
    @Test
    public void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2019, 1, 2));
        assertEquals(51314, problem.solve2().intValue());
    }
}