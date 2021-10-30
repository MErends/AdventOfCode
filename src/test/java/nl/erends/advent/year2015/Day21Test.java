package nl.erends.advent.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        assertEquals(78, problem.solve1().intValue());
        assertEquals(148, problem.solve2().intValue());
    }
}
