package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day18Test {

    @Test
    public void day18Test1() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2019, 18, 1));
        assertEquals(136, problem.solve1());
    }
}