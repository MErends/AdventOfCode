package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day05Test {

    @Test
    public void day05Test1() {
        List<String> input = Util.readInput(2015, 5, 1);
        Day05 problem = new Day05();
        assertEquals(2, problem.solve1(input).intValue());
    }
    
    @Test
    public void day05Test2() {
        List<String> input = Util.readInput(2015, 5, 2);
        Day05 problem = new Day05();
        assertEquals(2, problem.solve2(input).intValue());
    }
}
