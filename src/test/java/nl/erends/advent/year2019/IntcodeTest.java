package nl.erends.advent.year2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntcodeTest {

    @Test
    public void day01Test1() {
        Intcode intcode = new Intcode("1,9,10,3,2,3,11,0,99,30,40,50");
        intcode.execute();
        assertEquals(3500, intcode.getCode(0));
    }
}