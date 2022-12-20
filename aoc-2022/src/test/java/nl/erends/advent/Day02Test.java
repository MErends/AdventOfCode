package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day02Test {

    @Test
    void day02Test() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2022, 2, 1));
        assertThat(problem.solve1(), is (15));
        assertThat(problem.solve2(), is (12));
    }
}
