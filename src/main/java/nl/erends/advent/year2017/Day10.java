package nl.erends.advent.year2017;


import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        String inputLine = Util.getFileAsList("2017day10.txt").get(0);
        int chainlength = 256;
        List<Integer> inputs = new ArrayList<>();
        for (String number : inputLine.split(",")) {
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
        System.out.println(chain.get(0) * chain.get(1));



        List<String> lines =  Util.getFileAsList("2017day10.txt");
        for (String line : lines) {
            chain = new ArrayList<>();
            for (int i = 0; i < chainlength; i++) {
                chain.add(i);
            }
            inputs = new ArrayList<>();
            for (char c : line.toCharArray()) {
                inputs.add((int) c);
            }
            inputs.add(17);
            inputs.add(31);
            inputs.add(73);
            inputs.add(47);
            inputs.add(23);
            currentPosition = 0;
            skipSize = 0;
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
            System.out.println(outputString);
        }
    }
}
