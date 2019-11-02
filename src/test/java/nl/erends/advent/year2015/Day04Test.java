package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {

    @Test
    public void day04Test1() {
        String input = Util.readLine(2015, 4, 1);
        Day04 problem = new Day04();
        assertEquals(609043, problem.solve1(input).intValue());
    }

    @Test
    public void day04Test2() {
        String input = Util.readLine(2015, 4, 2);
        Day04 problem = new Day04();
        assertEquals(1048970, problem.solve1(input).intValue());
    }
}