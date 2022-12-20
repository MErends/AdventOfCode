package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day07Test {

    @Test
    void day07Test() {
        Day07 problem = new Day07();
        problem.setInput("16,1,2,0,4,2,7,1,2,14");
        assertThat(problem.solve1(), is(37));
        assertThat(problem.solve2(), is(168));
    }
}
