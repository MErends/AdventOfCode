package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

import java.util.List;

public class Day02 implements Problem<List<String>, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day02.class);
    private int answer2;
    
    public static void main(String[] args) {
        List<String> input = Util.readInput(2015, 2);
        Day02 problem = new Day02();
        LOG.info(problem.solve1(input));
        LOG.info(problem.answer2);
        Timer.printStats();
    }
    
    public Integer solve1(List<String> input) {
        Timer.start();
        int paper = 0;
        int lint = 0;
        for (String packing : input) {
            String[] dimensions = packing.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            int length = Integer.parseInt(dimensions[2]);
            paper += surface(width, height, length);
            paper += smallestSide(width, height, length);
            lint += ribbon(width, height, length);
            lint += bow(width, height, length);
        }
        Timer.end();
        answer2 = lint;
        return paper;
    }
    
    public Integer solve2(List<String> input) {
        solve1(input);
        return answer2;
    }

    private int surface(int width, int height, int length) {
        return 2 * width * height + 2 * width * length + 2 * height * length;
    }

    private int smallestSide(int width, int height, int length) {
        int smallestSide = width * height;
        smallestSide = Math.min(smallestSide, height * length);
        smallestSide = Math.min(smallestSide, width * length);
        return smallestSide;
    }

    private int ribbon(int width, int height, int length) {
        int smallestRibbon = 2 * width + 2 * height;
        smallestRibbon = Math.min(smallestRibbon, 2 * height + 2 * length);
        smallestRibbon = Math.min(smallestRibbon, 2 * width + 2 * length);
        return smallestRibbon;
    }

    private int bow(int width, int height, int length) {
        return width * height * length;
    }
}
