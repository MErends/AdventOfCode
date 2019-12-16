package nl.erends.advent.year2019;

import java.util.ArrayList;
import java.util.List;

class FFT {

    private FFT() {

    }

    static String apply(String inputString, int repeating) {
        List<Integer> input = new ArrayList<>();
        for (char c : inputString.toCharArray()) {
            input.add(Integer.parseInt("" + c));
        }
        StringBuilder output = new StringBuilder();
        int logstep = input.size() / 100;
        while (!input.isEmpty()) {
            if (input.size() % logstep == 0) {
                System.out.print((input.size() / logstep) + "-");
            }

            int result = 0;

            for (int pointer = 0; pointer < input.size(); pointer += 4 * repeating) {
                for (int step = 0; step < repeating && pointer + step < input.size(); step++) {
                    result += input.get(pointer + step);
                }
            }
            for (int pointer = 2 * repeating; pointer < input.size(); pointer += 4 * repeating) {
                for (int step = 0; step < repeating && pointer + step < input.size(); step++) {
                    result -= input.get(pointer + step);
                }
            }

            input.remove(0);
            output.append(Math.abs(result % 10));
            repeating++;
        }
        System.out.println();
        return output.toString();
    }

}
