package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {
    
    @Test
    void day20Test() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2020, 20, 1));
        assertThat(problem.solve1(), is(20899048083289L));
        assertThat(problem.solve2(), is(273));
    }
}
