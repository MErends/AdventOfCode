package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    @Test
    void day23Test() {
        Day23 problem = new Day23();
        problem.setInput("389125467");
        assertThat(problem.solve1(), is(67384529));
        assertThat(problem.solve2(), is(149245887792L));
    }
}
