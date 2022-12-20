package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day19Test {

    @Test
    void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2017, 19, 1));
        assertThat(problem.solve1(), is("SXPZDFJNRL"));
        assertThat(problem.solve2(), is("18126"));
    }
}
