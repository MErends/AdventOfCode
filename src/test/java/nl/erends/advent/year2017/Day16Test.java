package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput(Util.readLine(2017, 16, 1));
        assertEquals("cgpfhdnambekjiol", problem.solve1());
        assertEquals("gjmiofcnaehpdlbk", problem.solve2());
    }
}