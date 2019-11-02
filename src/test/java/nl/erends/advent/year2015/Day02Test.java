package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    
    @Test
    public void day02Test1() {
        List<String> input = Util.readInput(2015, 2, 1);
        Day02 problem = new Day02();
        assertEquals(101, problem.solve1(input).intValue());
        assertEquals(48, problem.solve2(input).intValue());
    }

}