package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day08Test {

    @Test
    public void day08Test1() {
        List<String> input = Util.readInput(2015, 8, 1);
        Day08 problem = new Day08();
        assertEquals(12, problem.solve1(input).intValue());
        assertEquals(19, problem.solve2(input).intValue());
    }
}
