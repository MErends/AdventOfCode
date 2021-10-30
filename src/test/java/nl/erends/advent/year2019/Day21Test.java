package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day21Test {

    @Test
    void day20Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readLine(2019, 21));
        assertEquals(19354173, problem.solve1());
    }

    @Test
    void day20Test2() {
        Day21 problem = new Day21();
        problem.setInput(Util.readLine(2019, 21));
        assertEquals(1145849660, problem.solve2());
    }
}
