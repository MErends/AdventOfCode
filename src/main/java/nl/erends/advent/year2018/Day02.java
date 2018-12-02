package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.*;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day02.txt");
        long start = System.currentTimeMillis();
        int hasDoubleChar = 0;
        int hasTripleChar = 0;
        for (String line : input) {
            Map<String, Long> lettermap = Arrays.stream(line.split("")).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            if (lettermap.values().stream().anyMatch(l -> l == 2)) hasDoubleChar++;
            if (lettermap.values().stream().anyMatch(l -> l == 3)) hasTripleChar++;
        }
        System.out.println(hasDoubleChar * hasTripleChar);
        long mid = System.currentTimeMillis();
        outer:
        for (int indexA = 0; indexA < input.size(); indexA++) {
            for (int indexB = indexA + 1; indexB < input.size(); indexB++) {
                String difference = difference(input.get(indexA), input.get(indexB));
                if (!difference.equals("")) {
                    System.out.println(difference);
                    break outer;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    
    private static String difference(String a, String b) {
        int differentIndex = -1;
        for (int index = 0; index < a.length(); index++) {
            if (a.charAt(index) != b.charAt(index)) {
                if (differentIndex == -1) {
                    differentIndex = index;
                } else {
                    return "";
                }
            }
        }
        return a.substring(0, differentIndex) + a.substring(differentIndex + 1);
    }
}
