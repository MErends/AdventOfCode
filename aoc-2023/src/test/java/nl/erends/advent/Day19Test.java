package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day19Test {

    @Test
    void day19Test() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2023, 19, 1));
        assertThat(problem.solve1(), is(19114));
        assertThat(problem.solve2(), is(167409079868000L));
    }
}
