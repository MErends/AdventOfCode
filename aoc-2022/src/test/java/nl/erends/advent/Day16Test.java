package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day16Test {

    @Test
    void day16Test() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2022, 16, 1));
        assertThat(problem.solve1(), is (1651));
        assertThat(problem.solve2(), is (1707));
    }
}
