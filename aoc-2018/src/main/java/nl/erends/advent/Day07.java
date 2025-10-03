package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Day07 extends AbstractProblem<List<String>, String> {

    private Map<String, String> steps;
    private int workerCount = 5;
    private int jobTime = 60;

    void main() {
        new Day07().setAndSolve(Util.readInput(2018, 7));
    }
    
    @Override
    public String solve1() {
        loadRequirements();
        StringBuilder order = new StringBuilder();
        while (!steps.isEmpty()) {
            String nextStep = steps.entrySet().stream()
                    .filter(e -> e.getValue().isEmpty())
                    .map(Map.Entry::getKey)
                    .sorted()
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
            order.append(nextStep);
            steps.remove(nextStep);
            steps.entrySet().stream()
                    .filter(e -> e.getValue().contains(nextStep))
                    .forEach(e -> e.setValue(e.getValue().replace(nextStep, "")));
        }
        return order.toString();
    }
    
    @Override
    public String solve2() {
        loadRequirements();
        int time = -1;
        List<Worker> workerList = new ArrayList<>();
        IntStream.rangeClosed(1, workerCount).forEach(i -> workerList.add(new Worker()));
        while (true) {
            time++;
            workerList.stream()
                    .filter(w -> w.busy)
                    .forEach(w -> w.timeLeft--);
            workerList.forEach(this::resetIfFinished);
            workerList.forEach(this::giveStepIfFree);
            if (workerList.stream().noneMatch(worker -> worker.busy)) {
                break;
            }
        }
        return Integer.toString(time);
    }

    private void giveStepIfFree(Worker worker) {
        if (worker.busy) {
            return;
        }
        String freeStep = null;
        for (Map.Entry<String, String> entry : steps.entrySet()) {
            if (entry.getValue() != null && entry.getValue().isEmpty()) {
                freeStep = entry.getKey();
                break;
            }
        }
        if (freeStep != null) {
            worker.busy = true;
            worker.processing = freeStep;
            worker.timeLeft = getTime(worker.processing);
            steps.put(freeStep, null);
        }
    }

    private void resetIfFinished(Worker worker) {
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

    private void loadRequirements() {
        steps = new HashMap<>();
        for (String line : input) {
            String[] words = line.split(" ");
            String step = words[7];
            String requires = words[1];
            steps.merge(step, requires, String::concat);
            steps.putIfAbsent(requires, "");
        }
    }
    
    private static class Worker {
        
        private boolean busy;
        private int timeLeft;
        private String processing;
    }
    
    private int getTime(String step) {
        return step.charAt(0) - 64 + jobTime;
    }

    void setTestParams() {
        this.workerCount = 2;
        this.jobTime = 0;
    }
}
