package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day06Test {

    @Test
    void day06Test1() {
        List<String> input = Util.readInput(2016, 6, 1);
        Day06 problem = new Day06();
        problem.setInput(input);
        assertThat(problem.solve1(), is("easter"));
        assertThat(problem.solve2(), is("advent"));
    }
}
