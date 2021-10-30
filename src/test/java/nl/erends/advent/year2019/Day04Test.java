package nl.erends.advent.year2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput("357253-892942");
        assertEquals(530, problem.solve1().intValue());
        assertEquals(324, problem.solve2().intValue());
    }
}