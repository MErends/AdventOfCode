package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day10Test {
    
    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput("192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12");
        assertThat(problem.solve1(), is("48705"));
    }

    @Test
    void day10Test2() {
        Day10 problem = new Day10();
        problem.setInput("AoC 2017");
        assertThat(problem.solve2(), is("33efeb34ea91902bb2f59c9920caa6cd"));
    }
}
