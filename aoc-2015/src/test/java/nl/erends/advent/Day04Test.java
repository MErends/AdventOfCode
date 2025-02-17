package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput("abcdef");
        problem.setStartNonce(600000);
        assertThat(problem.solve1(), is(609043));
    }

    @Test
    void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput("pqrstuv");
        problem.setStartNonce(1000000);
        assertThat(problem.solve1(), is(1048970));
    }
}
