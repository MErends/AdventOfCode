package nl.erends.advent.year2016;


import nl.erends.advent.util.Util;

import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        List<String> lines = Util.getFileAsList("2016day9.txt");
        for (String line : lines) {
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
            System.out.println(output.length());
        }

        lines = Util.getFileAsList("2016day9.txt");
        for (String line : lines) {
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
            System.out.println(decompressed);
        }
    }
}
