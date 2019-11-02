package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.IntStream;

public class Day07 {

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day07.txt");
        long start = System.currentTimeMillis();
        Map<String, String> steps = loadRequirements(input);
        StringBuilder order = new StringBuilder();
        while (!steps.isEmpty()) {
            String erase = "";
            for (Map.Entry<String, String> entry : steps.entrySet()) {
                if (entry.getValue().equals("")) {
                    erase = entry.getKey();
                }
            }
            order.append(erase);
            steps.remove(erase);
            for (Map.Entry<String, String> entry : steps.entrySet()) {
                if (entry.getValue().contains(erase)) {
                    steps.put(entry.getKey(), entry.getValue().replace(erase, ""));
                }
            }
        }
        System.out.println(order);
        long mid = System.currentTimeMillis();
        steps = loadRequirements(input);
        int time = -1;
        List<Worker> workerList = new ArrayList<>();
        IntStream.rangeClosed(1, 5).forEach(i -> workerList.add(new Worker()));
        while (!steps.isEmpty()) {
            time++;
            for (Worker worker : workerList) {
                if (worker.busy) {
                    worker.timeLeft--;
                }
            }
            for (Worker worker : workerList) {
                if (worker.busy && worker.timeLeft == 0) {
                    steps.remove(worker.processing);
                    for (Map.Entry<String, String> entry : steps.entrySet()) {
                        if (entry.getValue() != null && entry.getValue().contains(worker.processing)) {
                            steps.put(entry.getKey(), entry.getValue().replace(worker.processing, ""));
                        }
                    }
                    worker.busy = false;
                    worker.processing = null;
                }
            }
            for (Worker worker : workerList) {
                if (worker.busy) {
                    continue;
                }
                String freeStep = null;
                for (Map.Entry<String, String> entry : steps.entrySet()) {
                    if (entry.getValue() != null && entry.getValue().equals("")) {
                        freeStep = entry.getKey();
                        break;
                    }
                }
                if (freeStep == null) {
                    break;
                }
                worker.busy = true;
                worker.processing = freeStep;
                worker.timeLeft = getTime(worker.processing);
                steps.put(freeStep, null);
            }
        }
        System.out.println(time);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");    }

    private static Map<String, String> loadRequirements(List<String> input) {
        Map<String, String> steps = new TreeMap<>();
        for (String line : input) {
            String[] words = line.split(" ");
            String step = words[7];
            String requires = words[1];
            if (steps.containsKey(step)) {
                steps.put(step, steps.get(step) + requires);
            } else {
                steps.put(step, requires);
            }
            if (!steps.containsKey(requires)) {
                steps.put(requires, "");
            }
        }
        return steps;
    }
    
    private static class Worker {
        private boolean busy;
        private int timeLeft;
        private String processing;

        @Override
        public String toString() {
            return "Worker{" +
                    "busy=" + busy +
                    ", timeLeft=" + timeLeft +
                    ", processing='" + processing + '\'' +
                    '}';
        }
    }
    
    private static int getTime(String step) {
        return step.charAt(0) - 64 + 60;
    }
}
