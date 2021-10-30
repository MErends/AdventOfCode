package nl.erends.advent.year2015;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day12Test {

    @Test
    void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput("[1,2,3]");
        assertThat(problem.solve1(), is(6));
    }

    @Test
    void day12Test2() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":2,\"b\":4}");
        assertThat(problem.solve1(), is(6));
    }

    @Test
    void day12Test3() {
        Day12 problem = new Day12();
        problem.setInput("[[[3]]]");
        assertThat(problem.solve1(), is(3));
    }

    @Test
    void day12Test4() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":{\"b\":4},\"c\":-1}");
        assertThat(problem.solve1(), is(3));
    }

    @Test
    void day12Test5() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":[-1,1]}");
        assertThat(problem.solve1(), is(0));
    }

    @Test
    void day12Test6() {
        Day12 problem = new Day12();
        problem.setInput("[-1,{\"a\":1}]");
        assertThat(problem.solve1(), is(0));
    }

    @Test
    void day12Test7() {
        Day12 problem = new Day12();
        problem.setInput("[]");
        assertThat(problem.solve1(), is(0));
    }
    
    @Test
    void day12Test8() {
        Day12 problem = new Day12();
        problem.setInput("{}");
        assertThat(problem.solve1(), is(0));
    }
}
