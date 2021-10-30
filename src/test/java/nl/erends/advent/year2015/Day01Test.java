package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {
    
    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 1));
        assertThat(problem.solve1(), is(0));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 2));
        assertThat(problem.solve1(), is(0));
    }

    @Test
    void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 3));
        assertThat(problem.solve1(), is(3));
    }

    @Test
    void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 4));
        assertThat(problem.solve1(), is(3));
    }
    
    @Test
    void day01Test5() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 5));
        assertThat(problem.solve1(), is(3));
    }

    @Test
    void day01Test6() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 6));
        assertThat(problem.solve1(), is(-1));
    }

    @Test
    void day01Test7() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 7));
        assertThat(problem.solve1(), is(-1));
    }

    @Test
    void day01Test8() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 8));
        assertThat(problem.solve1(), is(-3));
    }

    @Test
    void day01Test9() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 9));
        assertThat(problem.solve1(), is(-3));
    }

    @Test
    void day01Test10() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 10));
        assertThat(problem.solve2(), is(1));
    }

    @Test
    void day01Test11() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 11));
        assertThat(problem.solve2(), is(5));
    }
}