package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {

    @Test
    public void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readLine(2019, 8, 1));
        assertEquals("2975", problem.solve1());
        
        problem = new Day08();
        problem.setInput(Util.readLine(2019, 8, 1));
        String expected = "#### #  # ###  #  # #### \n" +
                          "#    #  # #  # #  # #    \n" +
                          "###  #### #  # #  # ###  \n" +
                          "#    #  # ###  #  # #    \n" +
                          "#    #  # # #  #  # #    \n" +
                          "#### #  # #  #  ##  #### \n";
        assertEquals(expected, problem.solve2());
    }
}