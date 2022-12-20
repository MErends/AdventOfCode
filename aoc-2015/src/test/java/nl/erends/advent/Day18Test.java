package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day18Test {

    @Test
    void day18Test() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2015, 18, 1));
        problem.setTestIterations(4);
        assertThat(problem.solve1(), is(4));
        problem.setTestIterations(5);
        assertThat(problem.solve2(), is(17));
    }
}
