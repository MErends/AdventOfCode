package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntcodeTest {

    @Test
    public void day02Test1() {
        Intcode intcode = new Intcode("1,9,10,3,2,3,11,0,99,30,40,50");
        intcode.execute();
        assertEquals(3500, intcode.getCode(0));
    }

    @Test
    public void positionModeEqualsTest() {
        Intcode intcode = new Intcode("3,9,8,9,10,9,4,9,99,-1,8");
        intcode.getInput().add(8);
        intcode.execute();
        assertEquals(1, intcode.getOutput().get(0).intValue());
    }

    @Test
    public void day05Test1() {
        String input = Util.readLine(2019, 5, 1);

        Intcode intcode = new Intcode(input);
        intcode.getInput().add(7);
        intcode.execute();
        assertEquals(999, intcode.getOutput().get(0).intValue());

        intcode = new Intcode(input);
        intcode.getInput().add(8);
        intcode.execute();
        assertEquals(1000, intcode.getOutput().get(0).intValue());

        intcode = new Intcode(input);
        intcode.getInput().add(9);
        intcode.execute();
        assertEquals(1001, intcode.getOutput().get(0).intValue());
    }
}