package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static nl.erends.advent.util.Assert.assertEquals;

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
        intcode.addInput(8);
        intcode.execute();
        assertEquals(1, intcode.getOutput());
    }

    @Test
    public void day05Test1() {
        String input = Util.readLine(2019, 5, 1);

        Intcode intcode = new Intcode(input);
        intcode.addInput(7);
        intcode.execute();
        assertEquals(999, intcode.getOutput());

        intcode = new Intcode(input);
        intcode.addInput(8);
        intcode.execute();
        assertEquals(1000, intcode.getOutput());

        intcode = new Intcode(input);
        intcode.addInput(9);
        intcode.execute();
        assertEquals(1001, intcode.getOutput());
    }

    @Test
    public void quineTest() {
        String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        List<Long> outputList = new ArrayList<>();
        Intcode intcode = new Intcode(input);
        while (true) {
            intcode.execute();
            if (intcode.isHalted()) {
                break;
            } else {
                outputList.add(intcode.getOutput());
            }
        }
        String output = outputList.stream().map(String::valueOf).collect(Collectors.joining(","));
        org.junit.Assert.assertEquals(output, input);
    }
}