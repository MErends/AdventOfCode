package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day18Test {

    @Test
    void day18Test() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2020, 18, 1));
        assertEquals(71 + 51 + 26 + 437 + 12240 + 13632, problem.solve1());
        assertEquals(231 + 51 + 46 + 1445 + 669060 + 23340, problem.solve2());
    }
}
