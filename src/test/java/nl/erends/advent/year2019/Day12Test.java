package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day12Test {

    @Test
    public void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2019, 12, 1));
        problem.setMaxSteps(10);
        assertEquals(179, problem.solve1());
        assertEquals(2772, problem.solve2());
    }

    @Test
    public void day12Test2() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2019, 12, 2));
        problem.setMaxSteps(100);
        assertEquals(1940, problem.solve1());
        assertEquals(4686774924L, problem.solve2());
    }

}