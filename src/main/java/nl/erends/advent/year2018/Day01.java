package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day01 {

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day01.txt");
        long start = System.currentTimeMillis();
        List<Integer> integerInput = input.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(integerInput.stream().mapToInt(i -> i).sum());
        long mid = System.currentTimeMillis();
        Set<Integer> freqsSeen = new HashSet<>();
        int freq = 0;
        freqsSeen.add(freq);
        int index = 0;
        do {
            freq += integerInput.get(index % integerInput.size());
            index++;
        } while (freqsSeen.add(freq));
        System.out.println(freq);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
}
