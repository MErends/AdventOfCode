package nl.erends.advent.year2021;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day16Test {

    @Test
    void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput("8A004A801A8002F478");
        assertThat(problem.solve1(), is(16L));
    }

    @Test
    void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput("620080001611562C8802118E34");
        assertThat(problem.solve1(), is(12L));
    }

    @Test
    void day16Test3() {
        Day16 problem = new Day16();
        problem.setInput("C0015000016115A2E0802F182340");
        assertThat(problem.solve1(), is(23L));
    }

    @Test
    void day16Test4() {
        Day16 problem = new Day16();
        problem.setInput("A0016C880162017C3686B18A3D4780");
        assertThat(problem.solve1(), is(31L));
    }

    @Test
    void day16Test5() {
        Day16 problem = new Day16();
        problem.setInput("C200B40A82");
        assertThat(problem.solve2(), is(3L));
    }

    @Test
    void day16Test6() {
        Day16 problem = new Day16();
        problem.setInput("04005AC33890");
        assertThat(problem.solve2(), is(54L));
    }

    @Test
    void day16Test7() {
        Day16 problem = new Day16();
        problem.setInput("880086C3E88112");
        assertThat(problem.solve2(), is(7L));
    }

    @Test
    void day16Test8() {
        Day16 problem = new Day16();
        problem.setInput("CE00C43D881120");
        assertThat(problem.solve2(), is(9L));
    }

    @Test
    void day16Test9() {
        Day16 problem = new Day16();
        problem.setInput("D8005AC2A8F0");
        assertThat(problem.solve2(), is(1L));
    }

    @Test
    void day16Test10() {
        Day16 problem = new Day16();
        problem.setInput("F600BC2D8F");
        assertThat(problem.solve2(), is(0L));
    }

    @Test
    void day16Test11() {
        Day16 problem = new Day16();
        problem.setInput("9C005AC2F8F0");
        assertThat(problem.solve2(), is(0L));
    }

    @Test
    void day16Test12() {
        Day16 problem = new Day16();
        problem.setInput("9C0141080250320F1802104A08");
        assertThat(problem.solve2(), is(1L));
    }
}
