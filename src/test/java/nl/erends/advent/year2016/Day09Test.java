package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day09Test {
    
    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("ADVENT");
        assertThat(problem.solve1(), is(6));
    }

    @Test
    void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput("A(1x5)BC");
        assertThat(problem.solve1(), is(7));
    }

    @Test
    void day09Test3() {
        Day09 problem = new Day09();
        problem.setInput("(3x3)XYZ");
        assertThat(problem.solve1(), is(9));
        assertThat(problem.solve2(), is(9));
    }

    @Test
    void day09Test4() {
        Day09 problem = new Day09();
        problem.setInput("A(2x2)BCD(2x2)EFG");
        assertThat(problem.solve1(), is(11));
    }

    @Test
    void day09Test5() {
        Day09 problem = new Day09();
        problem.setInput("(6x1)(1x3)A");
        assertThat(problem.solve1(), is(6));
    }

    @Test
    void day09Test6() {
        Day09 problem = new Day09();
        problem.setInput("X(8x2)(3x3)ABCY");
        assertThat(problem.solve1(), is(18));
        assertThat(problem.solve2(), is(20));
    }

    @Test
    void day09Test7() {
        Day09 problem = new Day09();
        problem.setInput("(27x12)(20x12)(13x14)(7x10)(1x12)A");
        assertThat(problem.solve2(), is(241920));
    }

    @Test
    void day09Test8() {
        Day09 problem = new Day09();
        problem.setInput("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN");
        assertThat(problem.solve2(), is(445));
    }
}
