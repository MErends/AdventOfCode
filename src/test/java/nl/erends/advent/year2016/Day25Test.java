package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day25Test {

    @Test
    void day25Test1() {
        Day25 problem = new Day25();
        problem.setInput(Util.readInput(2016, 25, 1));
        problem.setTestValue();
        assertThat(problem.solve1(), is(158));
    }
}
