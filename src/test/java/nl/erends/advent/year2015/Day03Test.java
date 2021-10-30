package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 1));
        assertThat(problem.solve1(), is(2));
    }

    @Test
    void day03Test2() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 2));
        assertThat(problem.solve1(), is(4));
        assertThat(problem.solve2(), is(3));
    }

    @Test
    void day03Test3() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 3));
        assertThat(problem.solve1(), is(2));
        assertThat(problem.solve2(), is(11));
    }

    @Test
    void day03Test4() {
        Day03 problem = new Day03();
        problem.setInput(Util.readLine(2015, 3, 4));
        assertThat(problem.solve2(), is(3));
    }
}