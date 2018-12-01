package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 {

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day01.txt");
        long start = System.currentTimeMillis();
        System.out.println(input.stream().mapToInt(Integer::parseInt).sum());
        long mid = System.currentTimeMillis();
        Set<Integer> freqsSeen = new HashSet<>();
        int freq = 0;
        freqsSeen.add(freq);
        for (int index = 0; ; index++) {
            int change = Integer.parseInt(input.get(index % input.size()));
            freq += change;
            if (!freqsSeen.contains(freq)) {
                freqsSeen.add(freq);
            } else {
                System.out.println(freq);
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
}
