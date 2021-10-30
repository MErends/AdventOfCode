package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day13Test {

    @Test
    void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(Util.readLine(2019, 13, 1));
        assertEquals(376, problem.solve1());
        assertEquals(18509, problem.solve2());
    }
}
