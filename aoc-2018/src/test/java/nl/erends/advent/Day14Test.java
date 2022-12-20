package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(2018);
        assertThat(problem.solve1(), is("5941429882"));
        assertThat(problem.solve2(), is("86764"));
    }
}
