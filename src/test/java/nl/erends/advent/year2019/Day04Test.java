package nl.erends.advent.year2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {

    @Test
    public void day04Test1() {
        Day04 problem = new Day04();
        problem.setAndSolve("357253-892942");
        assertEquals(530, problem.solve1().intValue());
        assertEquals(324, problem.solve2().intValue());
    }
}