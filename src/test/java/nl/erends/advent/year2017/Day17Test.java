package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17Test {

    @Test
    public void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput(3);
        assertEquals(638, problem.solve1().intValue());
        assertEquals(1222153, problem.solve2().intValue());
    }
}