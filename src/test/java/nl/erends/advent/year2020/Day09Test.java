package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day09Test {
    
    @Test
    void day09Test() {
        Day09 problem = new Day09();
        problem.setInput(Util.readInput(2020, 9, 1));
        problem.setTestPreamble();
        assertThat(problem.solve1(), is(127L));
        assertThat(problem.solve2(), is(62L));
    }
}
