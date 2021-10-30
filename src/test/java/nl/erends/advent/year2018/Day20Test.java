package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readLine(2018, 20, 1));
        assertEquals(4108, problem.solve1().intValue());
        assertEquals(8366, problem.solve2().intValue());
    }
}