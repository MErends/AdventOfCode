package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

public class Day16 extends AbstractProblem<String, String> {
    
    private int targetLength = 272;


    static void main() {
        new Day16().setAndSolve("11100010111110100");
    }
    
    @Override
    public String solve1() {
        String input = "11100010111110100";
        while (input.length() < targetLength) {
            input = dragonCurve(input);
        }
        input = input.substring(0, targetLength);
        return checksum(input);
    }
    
    @Override
    public String solve2() {
        targetLength = 35651584;
        return solve1();
    }

    private String dragonCurve(String input) {
        StringBuilder output = new StringBuilder(input);
        output.append('0');
        char[] inputchars = input.toCharArray();
        for (int index = inputchars.length - 1; index >= 0; index--) {
            output.append(inputchars[index] == '1' ? '0' : '1');
        }
        return output.toString();
    }

    private String checksum(String input) {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < input.length(); index += 2) {
            String subString = input.substring(index, index + 2);
            output.append(subString.charAt(0) == subString.charAt(1) ? '1' : '0');
        }
        if (output.length() % 2 == 0) {
            return checksum(output.toString());
        }
        return output.toString();
    }
}
