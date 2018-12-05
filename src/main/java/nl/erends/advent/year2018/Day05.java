package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

public class Day05 {
    
    public static void main(String[] args) {
        String input = FileIO.getFileAsList("2018day05.txt").get(0);
        String part1Input = input;
        long start = System.currentTimeMillis();
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
        System.out.println(part1Input.length());
        long mid = System.currentTimeMillis();
        int smallestPolymer = Integer.MAX_VALUE;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            String input2 = input;
            input2 = input2.replace("" + letter, "");
            input2 = input2.replace("" + Character.toUpperCase(letter), "");
            lengthBefore = input2.length();
            lengthAfter = 0;
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
        System.out.println(smallestPolymer);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    private static boolean isPair(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        return (!Character.isLowerCase(first) || !Character.isLowerCase(second))
                && (!Character.isUpperCase(first) || !Character.isUpperCase(second))
                && Character.toLowerCase(first) == Character.toLowerCase(second);
    }
}
