package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day19Test {

    @Test
    void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readLine(2019, 19, 1));
        assertThat(problem.solve1(), is(138));
        assertThat(problem.solve2(), is(13530764));
    }
}