package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day09Test {

    @Test
    public void day09Test1() {
        List<String> input = Util.readInput(2015, 9, 1);
        Day09 problem = new Day09();
        assertEquals(605, problem.solve1(input).intValue());
        problem = new Day09();
        assertEquals(982, problem.solve2(input).intValue());
    }
}
