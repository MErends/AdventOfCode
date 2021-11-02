package nl.erends.advent.year2017;

import java.util.ArrayList;
import java.util.List;

class KnotHasher {
    
    private KnotHasher() {
    }
    
    public static String hash(String inputString) {
        List<Integer> inputs = new ArrayList<>();
        for (char c : inputString.toCharArray()) {
            inputs.add((int) c);
        }
        List<Integer> chain = new ArrayList<>();
        int chainlength = 256;
        for (int i = 0; i < chainlength; i++) {
            chain.add(i);
        }
        inputs.add(17);
        inputs.add(31);
        inputs.add(73);
        inputs.add(47);
        inputs.add(23);
        int currentPosition = 0;
        int skipSize = 0;
        for (int round = 0; round < 64; round++) {
            for (int input : inputs) {
                for (int offset = 0; offset < input / 2; offset++) {
                    int temp = chain.get((currentPosition + offset) % chainlength);
                    chain.set((currentPosition + offset) % chainlength, chain.get((currentPosition + input - offset - 1) % chainlength));
                    chain.set((currentPosition + input - offset - 1) % chainlength, temp);
                }
                currentPosition = (currentPosition + input + skipSize) % chainlength;
                skipSize++;
            }
        }
        List<Integer> output = new ArrayList<>();
        for (int index = 0; index < 16; index++) {
            int letter = 0;
            List<Integer> subChain = chain.subList(index * 16, index * 16 + 16);
            for (int value : subChain) {
                letter ^= value;
            }
            output.add(letter);
        }
        StringBuilder outputString = new StringBuilder();
        for (int value : output) {
            if (value < 16) {
                outputString.append("0");
            }
            outputString.append(Integer.toHexString(value));
        }
        return outputString.toString();
    }
}
