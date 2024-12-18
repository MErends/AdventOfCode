package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day18Test {

    @Test
    void day18Test1() {
        Day18 problem = new Day18();
        problem.setTestRanges();
        problem.setInput(Util.readInput(2024, 18, 1));
        assertThat(problem.solve1(), is ("22"));
        assertThat(problem.solve2(), is("6,1"));
    }
}
