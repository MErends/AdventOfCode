package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day25Test {

    @Test
    void day24Test1() {
        Day25 problem = new Day25();
        problem.setInput(Util.readInput(2018, 25, 1));
        assertThat(problem.solve1(), is(2));
    }
}
