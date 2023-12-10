package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, 1));
        assertThat(problem.solve1(), is(4));
    }

    @Test
    void day10Test2() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, 2));
        assertThat(problem.solve1(), is(8));
    }

    @Test
    void day10Test3() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, 3));
        assertThat(problem.solve2(), is(4));
    }

    @Test
    void day10Test4() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, 4));
        assertThat(problem.solve2(), is(8));
    }

    @Test
    void day10Test5() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, 5));
        assertThat(problem.solve2(), is(10));
    }
}
