package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day06Test {

    @Test
    void day06Test() {
        Day06 problem = new Day06();
        problem.setInput("3,4,3,1,2");
        assertThat(problem.solve1(), is(5934L));
        assertThat(problem.solve2(), is(26984457539L));
    }
}
