package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(">");
        assertThat(problem.solve1(), is(2));
    }

    @Test
    void day03Test2() {
        Day03 problem = new Day03();
        problem.setInput("^>v<");
        assertThat(problem.solve1(), is(4));
        assertThat(problem.solve2(), is(3));
    }

    @Test
    void day03Test3() {
        Day03 problem = new Day03();
        problem.setInput("^v^v^v^v^v");
        assertThat(problem.solve1(), is(2));
        assertThat(problem.solve2(), is(11));
    }

    @Test
    void day03Test4() {
        Day03 problem = new Day03();
        problem.setInput("^v");
        assertThat(problem.solve2(), is(3));
    }
}
