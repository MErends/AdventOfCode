package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

public class Day01 implements Problem<String, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day01.class);
    private int answer2;

    public static void main(String[] args) {
        String input = Util.readInput(2015, 1).get(0);
        Day01 problem = new Day01();
        LOG.info(problem.solve1(input));
        LOG.info(problem.answer2);
        Timer.printStats();
    }
    public Integer solve1(String input) {
        Timer.start();
        int floor = 0;
        int position = 0;
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            position++;
            if (c == '(') {
                floor++;
            } else {
                floor--;
            }
            if (floor == -1 && answer2 == 0) {
                Timer.end2();
                answer2 = position;
            }
        }
        Timer.end1();
        return floor;
    }

    public Integer solve2(String input) {
        solve1(input);
        return answer2;
    }
}
