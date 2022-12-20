package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    @Test
    void day23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readLine(2019, 23, 1));
        assertThat(problem.solve1(), is(24602L));
        assertThat(problem.solve2(), is(19641L));
    }
}
