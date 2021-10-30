package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day15Test {

    @Test
    void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readLine(2019, 15, 1));
        assertEquals(258, problem.solve1());
        problem = new Day15();
        problem.setInput(Util.readLine(2019, 15, 1));
        assertEquals(372, problem.solve2());
    }
}