package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day06Test {

    @Test
    public void day06Test1() {
        List<String> input = Util.readInput(2016, 6, 1);
        Day06 problem = new Day06();
        problem.setInput(input);
        assertEquals("easter", problem.solve1());
        assertEquals("advent", problem.solve2());
    }
}
