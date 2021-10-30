package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readLine(2015, 4, 1));
        assertEquals(609043, problem.solve1().intValue());
    }

    @Test
    void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput(Util.readLine(2015, 4, 2));
        assertEquals(1048970, problem.solve1().intValue());
    }
}