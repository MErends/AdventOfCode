package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day21Test {

    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        assertThat(problem.solve1(), is(78));
        assertThat(problem.solve2(), is(148));
    }
}
