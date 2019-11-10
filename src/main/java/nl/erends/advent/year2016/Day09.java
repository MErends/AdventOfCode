package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day09 extends AbstractProblem<String, Long> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readLine(2016, 9));
    }
    
    @Override
    public Long solve1() {
        String line = input;
        StringBuilder output = new StringBuilder();
        while (line.contains("(")) {
            int split = line.indexOf('(');
            output.append(line.substring(0, split));
            line = line.substring(split + 1);
            split = line.indexOf(')');
            String marker = line.substring(0, split);
            line = line.substring(split + 1);
            int numChars = Integer.parseInt(marker.split("x")[0]);
            int repeat = Integer.parseInt(marker.split("x")[1]);
            while (repeat != 0) {
                output.append(line.substring(0, numChars));
                repeat--;
            }
            line = line.substring(numChars);
        }
        output.append(line);
        return (long) output.length();
    }
    
    @Override
    public Long solve2() {
        String line = input;
        StringBuilder input = new StringBuilder(line);
        long decompressed = 0;
        while (input.indexOf("(") != -1) {
            int split = input.indexOf("(");
            decompressed += split;
            input.delete(0, split + 1);
            split = input.indexOf(")");
            String marker = input.substring(0, split);
            input.delete(0, split + 1);
            int numChars = Integer.parseInt(marker.split("x")[0]);
            int repeat = Integer.parseInt(marker.split("x")[1]);
            String repeatedString = input.substring(0, numChars);
            input.delete(0, numChars);
            while (repeat != 0) {
                input.insert(0, repeatedString);
                repeat--;
            }
        }
        decompressed += input.length();
        return decompressed;
    }
}
