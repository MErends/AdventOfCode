package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readLine(2025, 2, 1));
        assertThat(problem.solve1(), is(1227775554L));
        assertThat(problem.solve2(), is(4174379265L));
    }
}
