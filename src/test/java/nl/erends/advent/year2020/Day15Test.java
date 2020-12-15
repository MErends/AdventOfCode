package nl.erends.advent.year2020;

import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test() {
        Day15 problem = new Day15();
        problem.setInput("0,3,6");
        assertEquals(436, problem.solve1());
        assertEquals(175594, problem.solve2());
    }
}
