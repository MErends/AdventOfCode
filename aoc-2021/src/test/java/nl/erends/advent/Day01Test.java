package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {

    @Test
    void day01Test() {
        Day01 problem = new Day01();
        problem.setInput(Util.readIntegers(2021, 1, 1));
        assertThat(problem.solve1(), is(7));
        assertThat(problem.solve2(), is(5));
    }
}
