package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 extends AbstractProblem<List<String>, Number> {
    
    private final Map<String, Character> mapping = new HashMap<>();
    private final List<Integer> sums = new ArrayList<>();


    public static void main(String[] args) {
        new Day12().setAndSolve(Util.readInput(2018, 12));
    }
    
    @Override
    public Integer solve1() {
        State state = new State(readInput());
        for (int i = 0; i < 20; i++) {
            state.iterate();
        }
        return state.getSum();
    }

    @Override
    public Long solve2() {
        State state = new State(readInput());
        sums.add(state.getSum());
        while (sums.size() < 100 || !isLinear()) {
            state.iterate();
            sums.add(state.getSum());
        }
        int index = sums.size() - 1;
        int sum = sums.get(index);
        long slope = (long) sums.get(index) - sums.get(index - 1);
        long constant = sum - (slope * index);
        return slope * 50_000_000_000L + constant;
    }

    private String readInput() {
        String state = "";
        for (String line : input) {
            if (line.contains(":")) {
                state = line.split(" ")[2];
            } else if (line.contains("=")) {
                String[] words = line.split(" ");
                mapping.put(words[0], words[2].charAt(0));
            }
        }
        return state;
    }
    
    private boolean isLinear() {
        int sumSize = sums.size();
        int dif1 = sums.get(sumSize - 1) - sums.get(sumSize - 2);
        int dif2 = sums.get(sumSize - 2) - sums.get(sumSize - 3);
        return dif1 == dif2;
    }

    private class State {
        
        private String stringState;
        private int startIndex = 0;
        
        State(String stringState) {
            this.stringState = stringState;
        }
        
        void iterate() {
            StringBuilder nextState = new StringBuilder();
            stringState = "....." + stringState + ".....";
            startIndex -= 3;
            for (int pointer = 0; pointer < stringState.length() - 5; pointer++) {
                String substring = stringState.substring(pointer, pointer + 5);
                nextState.append(mapping.getOrDefault(substring, '.'));
            }
            while (nextState.charAt(0) == '.') {
                nextState.deleteCharAt(0);
                startIndex++;
            }
            while (nextState.charAt(nextState.length() - 1) == '.') {
                nextState.deleteCharAt(nextState.length() - 1);
            }
            stringState = nextState.toString();
        }
        
        int getSum() {
            int sum = 0;
            int value = startIndex;
            for (char c : stringState.toCharArray()) {
                sum += c == '#' ? value : 0;
                value++;
            }
            return sum;
        }
    }
}
