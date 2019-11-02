package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

import java.util.List;

public class Day08 implements Problem<List<String>, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day08.class);
    
    public static void main(String[] args) {
        List<String> lines = Util.readInput(2015, 8);
        Day08 problem = new Day08();
        LOG.info(problem.solve1(lines));
        LOG.info(problem.solve2(lines));
        Timer.printStats();
    }


    public Integer solve1(List<String> input) {
        Timer.start1();
        int totalDiff = 0;
        for (String line : input) {
            int difference = 2;
            line = line.substring(1, line.length() - 1);
            int pointer = 0;
            while (pointer < line.length()) {
                char c = line.charAt(pointer);
                if (c == '\\') {
                    char c1 = line.charAt(pointer + 1);
                    if (c1 == 'x') {
                        difference += 3;
                        pointer += 3;
                    } else {
                        difference += 1;
                        pointer++;
                    }
                }
                pointer++;
            }
            totalDiff += difference;
        }
        Timer.end1();
        return totalDiff;
    }
    
    public Integer solve2(List<String> input) {
        Timer.start2();
        int totalDiff = 0;
        for (String line : input) {
            totalDiff += 2;
            for (char c : line.toCharArray()) {
                if (c == '\\' || c == '\"') {
                    totalDiff++;
                }
            }
        }
        Timer.end2();
        return totalDiff;
    }
}
