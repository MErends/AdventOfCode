package nl.erends.advent.year2015;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput("abcdef");
        assertThat(problem.solve1(), is(609043));
    }

    @Test
    void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput("pqrstuv");
        assertThat(problem.solve1(), is(1048970));
    }
}
