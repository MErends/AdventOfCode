package nl.erends.advent.year2020;

import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day15Test {

    @Test
    void day15Test() {
        Day15 problem = new Day15();
        problem.setInput("0,3,6");
        assertEquals(436, problem.solve1());
        assertEquals(175594, problem.solve2());
    }
}
