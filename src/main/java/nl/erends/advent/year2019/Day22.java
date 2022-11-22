package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;


public class Day22 extends AbstractProblem<List<String>, Number> { // 12_077_247_208+
    
    private static final int NUM_CARDS = 10007;
    private static final long MANY_CARDS = 119_315_717_514_047L; //82393 repeats after 4
    private static final long MANY_ITERATIONS = 101_741_582_076_661L;




    public static void main(String[] args) {
        new Day22().setAndSolve(Util.readInput(2019, 22));
    }

    @Override
    public Number solve1() {
        List<IntUnaryOperator> operators = new ArrayList<>();
        for (String line : input) {
            String[] words = line.split(" ");
            if (words[0].equals("cut")) {
                operators.add(input -> (input + NUM_CARDS - Integer.parseInt(words[1])) % NUM_CARDS);
            } else if (words[2].equals("increment")) {
                operators.add(input -> input * Integer.parseInt(words[3]) % NUM_CARDS);
            } else {
                operators.add(input -> NUM_CARDS - input - 1);
            }
        }
        int position = 2019;
        for (IntUnaryOperator op : operators) {
            position = op.applyAsInt(position);
        }
        
        return position;
    }
    @Override
    public Number solve2() {
        List<LongUnaryOperator> operators = new ArrayList<>();
        for (String line : input) {
            // String[] words = line.split(" ");
            // if (words[0].equals("cut")) {
            //     operators.add(input -> (input + MANY_CARDS + Long.parseLong(words[1])) % MANY_CARDS);
            // } else if (words[2].equals("increment")) {
            //     operators.add(input -> {
            //         while (input % Long.parseLong(words[3]) != 0) {
            //             input += MANY_CARDS;
            //         }
            //         return input / Long.parseLong(words[3]);
            //     });
            // } else {
            //     operators.add(input -> MANY_CARDS - input - 1);
            // }
            String[] words = line.split(" ");
            if (words[0].equals("cut")) {
                operators.add(input -> (input + MANY_CARDS - Integer.parseInt(words[1])) % MANY_CARDS);
            } else if (words[2].equals("increment")) {
                operators.add(input -> input * Integer.parseInt(words[3]) % MANY_CARDS);
            } else {
                operators.add(input -> MANY_CARDS - input - 1);
            }
        }
        // Collections.reverse(operators);
        long pos = 0;
        long shuffle = 0;
        do {
            for (LongUnaryOperator op : operators) {
                pos = op.applyAsLong(pos);
            }
            shuffle++;
            Timer.tick(shuffle);
        } while (pos != 0);
        return shuffle;
    }
}

