package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void day16Test1() {
        List<String> input = Util.readInput(2015, 16, 1);
        Day16 problem = new Day16();
        assertEquals(373, problem.solve1(input).intValue());
        problem = new Day16();
        assertEquals(260, problem.solve2(input).intValue());
    }
}
