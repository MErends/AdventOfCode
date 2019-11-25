package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day05 extends AbstractProblem<String, Integer> {
    
    public static void main(String[] args) {
        new Day05().setAndSolve(Util.readLine(2018, 5));
    }
    
    @Override
    public Integer solve1() {
        String part1Input = input;
        int lengthBefore = part1Input.length();
        int lengthAfter = 0;
        while (lengthBefore != lengthAfter) {
            lengthBefore = part1Input.length();
            for (int index = 0; index < part1Input.length() - 1; index++) {
                String pair = part1Input.substring(index, index + 2);
                if (isPair(pair)) {
                    part1Input = part1Input.substring(0, index) + part1Input.substring(index + 2);
                    lengthAfter = part1Input.length();
                }
            }
        }
        return part1Input.length();
    }
    
    @Override
    public Integer solve2() {
        int smallestPolymer = Integer.MAX_VALUE;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            String input2 = input;
            input2 = input2.replace("" + letter, "");
            input2 = input2.replace("" + Character.toUpperCase(letter), "");
            int lengthBefore = input2.length();
            int lengthAfter = 0;
            while (lengthBefore != lengthAfter) {
                lengthBefore = input2.length();
                for (int index = 0; index < input2.length() - 1; index++) {
                    String pair = input2.substring(index, index + 2);
                    if (isPair(pair)) {
                        input2 = input2.substring(0, index) + input2.substring(index + 2);
                        lengthAfter = input2.length();
                    }
                }
            }
            smallestPolymer = Math.min(smallestPolymer, input2.length());
        }
        return smallestPolymer;
    }
    
    private boolean isPair(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        return (!Character.isLowerCase(first) || !Character.isLowerCase(second))
                && (!Character.isUpperCase(first) || !Character.isUpperCase(second))
                && Character.toLowerCase(first) == Character.toLowerCase(second);
    }
}
