package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    @Test
    public void day15Test1() {
        List<String> input = Util.readInput(2015, 15, 1);
        Day15 problem = new Day15();
        assertEquals(62842880, problem.solve1(input).intValue());
        problem = new Day15();
        assertEquals(57600000, problem.solve2(input).intValue());
    }
}
