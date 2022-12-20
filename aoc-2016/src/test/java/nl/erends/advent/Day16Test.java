package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day16Test {

    @Test
    void day15Test1() {
        Day16 problem = new Day16();
        problem.setInput("11100010111110100");
        assertThat(problem.solve1(), is("10100011010101011"));
        assertThat(problem.solve2(), is("01010001101011001"));
    }
}
