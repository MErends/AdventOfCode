package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Day1Test {
    @Test
    public void solve1() throws Exception {
        assertEquals(Day1.solve1("1122"), 3);
        assertEquals(Day1.solve1("1111"), 4);
        assertEquals(Day1.solve1("1234"), 0);
        assertEquals(Day1.solve1("91212129"), 9);
    }

    @Test
    public void solve2() throws Exception {
        assertEquals(Day1.solve2("1212"), 6);
        assertEquals(Day1.solve2("1221"), 0);
        assertEquals(Day1.solve2("123425"), 4);
        assertEquals(Day1.solve2("123123"), 12);
        assertEquals(Day1.solve2("12131415"), 4);
    }

}