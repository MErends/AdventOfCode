package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day19Test {

    @Test
    public void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readLine(2019, 19, 1));
        assertEquals(138, problem.solve1());
        assertEquals(13530764, problem.solve2());
    }
}