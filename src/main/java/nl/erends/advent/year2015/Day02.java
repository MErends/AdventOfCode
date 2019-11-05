package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day02 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2015, 2));
    }
    
    @Override
    public Integer solve1() {
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
        answer2 = lint;
        return paper;
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
