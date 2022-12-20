package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day10 extends AbstractProblem<String, String> {

    public static void main(String[] args) {
        new Day10().setAndSolve(Util.readLine(2017, 10));
    }
    
    @Override
    public String solve1() {
        int chainlength = 256;
        List<Integer> inputs = new ArrayList<>();
        for (String number : input.split(",")) {
            inputs.add(Integer.parseInt(number));
        }
        List<Integer> chain = new ArrayList<>();
        for (int i = 0; i < chainlength; i++) {
            chain.add(i);
        }
        int currentPosition = 0;
        int skipSize = 0;
        for (int input : inputs) {
            for (int offset = 0; offset < input / 2; offset++) {
                int temp = chain.get((currentPosition + offset) % chainlength);
                chain.set((currentPosition + offset) % chainlength, chain.get((currentPosition + input - offset - 1) % chainlength));
                chain.set((currentPosition + input - offset - 1) % chainlength, temp);
            }
            currentPosition = (currentPosition + input + skipSize) % chainlength;
            skipSize++;
        }
        return Integer.toString(chain.get(0) * chain.get(1));
    }
    
    @Override
    public String solve2() {
        return KnotHasher.hash(input);
    }
}
