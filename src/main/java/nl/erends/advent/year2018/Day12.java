package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {


    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day12.txt");
        long start = System.currentTimeMillis();
        long mid = System.currentTimeMillis();
        String state = "";
        Map<String, Character> mapping = new HashMap<>();
        for (String line : input) {
            if (line.contains(":")) {
                state = line.split(" ")[2];
            } else if (line.contains("=")) {
                String[] words = line.split(" ");
                mapping.put(words[0], words[2].charAt(0));
            }
        }
        int startingIndex = 0;
        int iteration = 0;
        int targetIteration = 20;
        int oldOldIncrement;
        int oldIncrement = 0;
        int increment = 0;
        while (true) {
            int sumBefore = getSum(state, startingIndex);
            if (iteration == targetIteration) {
                System.out.println(getSum(state, startingIndex));
                mid = System.currentTimeMillis();
            }
            boolean extended = false;
            StringBuilder newState = new StringBuilder();
            String chunk = "...." + state.substring(0, 1);
            char newChar = mapping.get(chunk);
            if (newChar == '#') {
                newState.append(newChar);
                startingIndex -= 2;
                extended = true;
            }
            chunk = "..." + state.substring(0, 2);
            newChar = mapping.get(chunk);
            if (newChar == '#') {
                newState.append(newChar);
                if (!extended)
                    startingIndex--;
            } else if (extended) {
                newState.append('.');
            }
            chunk = ".." + state.substring(0, 3);
            newState.append(mapping.get(chunk));
            chunk = "." + state.substring(0, 4);
            newState.append(mapping.get(chunk));
            for (int index = 0; index < state.length() - 4; index++) {
                newState.append(mapping.get(state.substring(index, index + 5)));
            }
            newState.append(mapping.get(state.substring(state.length() - 4) + "."));
            newState.append(mapping.get(state.substring(state.length() - 3) + ".."));
            newState.append(mapping.get(state.substring(state.length() - 2) + "..."));
            newState.append(mapping.get(state.substring(state.length() - 1) + "...."));
            while (newState.substring(newState.length() - 1).equals(".")) {
                newState.deleteCharAt(newState.length() - 1);
            }
            while (newState.substring(0, 1).equals(".")) {
                newState.deleteCharAt(0);
                startingIndex++;
            }
            state = newState.toString();
            iteration++;
            int sumAfter = getSum(state, startingIndex);
            oldOldIncrement = oldIncrement;
            oldIncrement = increment;
            increment = sumAfter - sumBefore;
            if (oldOldIncrement == oldIncrement && oldIncrement == increment) {
                break;
            }
            if (iteration == Integer.MAX_VALUE) {
                break;
            }
        }
        System.out.println(getSum(state, startingIndex) + (50_000_000_000L - iteration) * increment);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }

    private static int getSum(String state, int startingIndex) {
        int sum = 0;
        for (char c : state.toCharArray()) {
            sum += c == '#' ? startingIndex : 0;
            startingIndex++;
        }
        return sum;
    }
}
