package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day07Test {

    @Test
    public void day07Test1() {
        List<String> input = Util.readInput(2015, 7, 1);
        Day07 problem = new Day07();
        assertEquals(492, problem.solve1(input).intValue());
        assertEquals(65043, problem.solve2(input).intValue());
    }
}
