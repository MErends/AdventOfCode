package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day16Test {

    @Test
    void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput("80871224585914546619083218645595");
        assertThat(problem.solve1(), is("24176176"));
    }

    @Test
    void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput("03036732577212944063491565474664");
        assertThat(problem.solve2(), is("84462026"));
    }
}
