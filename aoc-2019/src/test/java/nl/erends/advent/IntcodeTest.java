package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IntcodeTest {

    @Test
    void day02Test1() {
        Intcode intcode = new Intcode("1,9,10,3,2,3,11,0,99,30,40,50");
        intcode.execute();
        assertThat(intcode.getCode(0), is(3500L));
    }

    @Test
    void positionModeEqualsTest() {
        Intcode intcode = new Intcode("3,9,8,9,10,9,4,9,99,-1,8");
        intcode.addInput(8);
        intcode.execute();
        assertThat(intcode.getOutput(), is(1L));
    }

    @Test
    void day05Test1() {
        String input = Util.readLine(2019, 5, 1);

        Intcode intcode = new Intcode(input);
        intcode.addInput(7);
        intcode.execute();
        assertThat(intcode.getOutput(), is(999L));

        intcode = new Intcode(input);
        intcode.addInput(8);
        intcode.execute();
        assertThat(intcode.getOutput(), is(1000L));

        intcode = new Intcode(input);
        intcode.addInput(9);
        intcode.execute();
        assertThat(intcode.getOutput(), is(1001L));
    }

    @Test
    void quineTest() {
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
        assertThat(input, is(output));
    }
    
    @Test
    void easterEggTest() {
        Intcode intcode = new Intcode(Util.readLine(2021, 7, 1));
        StringBuilder sb = new StringBuilder();
        while (true) {
            intcode.execute();
            if (intcode.isHalted()) {
                break;
            } else {
                sb.append((char) intcode.getOutput().longValue());
            }
        }
        assertThat(sb.toString(), is("Ceci n'est pas une intcode program\n"));
    }
}
