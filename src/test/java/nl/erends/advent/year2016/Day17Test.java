package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput("ihgpwlah");
        assertThat(problem.solve1(), is("DDRRRD"));
        assertThat(problem.solve2(), is("370"));
    }

    @Test
    void day17Test2() {
        Day17 problem = new Day17();
        problem.setInput("kglvqrro");
        assertThat(problem.solve1(), is("DDUDRLRRUDRD"));
        assertThat(problem.solve2(), is("492"));
    }

    @Test
    void day17Test3() {
        Day17 problem = new Day17();
        problem.setInput("ulqzkmiv");
        assertThat(problem.solve1(), is("DRURDRUDDLLDLUURRDULRLDUUDDDRR"));
        assertThat(problem.solve2(), is("830"));
    }
}
