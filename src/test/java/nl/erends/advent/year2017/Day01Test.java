package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day01Test {
    
    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput("1122");
        assertThat(problem.solve1(), is(3));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput("1111");
        assertThat(problem.solve1(), is(4));
    }

    @Test
    void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput("1234");
        assertThat(problem.solve1(), is(0));
    }

    @Test
    void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput("91212129");
        assertThat(problem.solve1(), is(9));
    }

    @Test
    void day01Test5() {
        Day01 problem = new Day01();
        problem.setInput("1212");
        assertThat(problem.solve2(), is(6));
    }

    @Test
    void day01Test6() {
        Day01 problem = new Day01();
        problem.setInput("1221");
        assertThat(problem.solve2(), is(0));
    }

    @Test
    void day01Test7() {
        Day01 problem = new Day01();
        problem.setInput("123425");
        assertThat(problem.solve2(), is(4));
    }

    @Test
    void day01Test8() {
        Day01 problem = new Day01();
        problem.setInput("123123");
        assertThat(problem.solve2(), is(12));
    }

    @Test
    void day01Test9() {
        Day01 problem = new Day01();
        problem.setInput("12131415");
        assertThat(problem.solve2(), is(4));
    }
}