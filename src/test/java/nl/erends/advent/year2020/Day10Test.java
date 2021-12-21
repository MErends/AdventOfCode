package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readIntegers(2020, 10, 1));
        assertThat(problem.solve1(), is(7 * 5));
        assertThat(problem.solve2(), is(8L));
    }

    @Test
    void day10Test2() {
        Day10 problem = new Day10();
        problem.setInput(Util.readIntegers(2020, 10, 2));
        assertThat(problem.solve1(), is(22 * 10));
        assertThat(problem.solve2(), is(19208L));
    }
}
