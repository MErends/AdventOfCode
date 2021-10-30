package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(Util.readInput(2015, 14, 1));
        problem.setMaxSeconds(1000);
        assertEquals(1120, problem.solve1().intValue());
        assertEquals(689, problem.solve2().intValue());
    }
}
