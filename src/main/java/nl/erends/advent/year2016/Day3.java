package nl.erends.advent.year2016;


import nl.erends.advent.util.Util;

import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2016day3.txt");
        System.out.println(solve1(input));
        System.out.println(solve2(input));
    }

    private static int solve1(List<String> input) {
        int possible = 0;
        for (String line : input) {
            int a = Integer.parseInt(line.substring(0, 5).trim());
            int b = Integer.parseInt(line.substring(5,10).trim());
            int c = Integer.parseInt(line.substring(10).trim());
            if (a >= b && a >= c && a < b + c) {
                possible++;
            } else if (b >=a && b >= c && b < a + c) {
                possible++;
            } else if (c >= a && c >= b && c < a + b) {
                possible++;
            }
        }
        return possible;
    }

    private static int solve2(List<String> input) {
        int possible = 0;
        for (int index = 0; index < input.size() - 2; index += 3) {
            String line1 = input.get(index);
            String line2 = input.get(index + 1);
            String line3 = input.get(index + 2);
            for (int column = 0; column < 3; column++) {
                int a = Integer.parseInt(line1.substring(column * 5, column * 5 + 5).trim());
                int b = Integer.parseInt(line2.substring(column * 5, column * 5 + 5).trim());
                int c = Integer.parseInt(line3.substring(column * 5, column * 5 + 5).trim());
                if (a >= b && a >= c && a < b + c) {
                    possible++;
                } else if (b >=a && b >= c && b < a + c) {
                    possible++;
                } else if (c >= a && c >= b && c < a + b) {
                    possible++;
                }
            }
        }
        return possible;
    }
}
