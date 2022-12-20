package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day18Test {

    @Test
    void day18Test() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2022, 18, 1));
        assertThat(problem.solve1(), is (64L));
        assertThat(problem.solve2(), is (58L));
    }
}
