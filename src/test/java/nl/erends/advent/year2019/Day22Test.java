package nl.erends.advent.year2019;

import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nl.erends.advent.year2017.Day23.isPrime;


class Day22Test {


    @Test
    @Disabled
    void test() {
        int[] NUM_CARDS = new int[]{11};
        List<String> input = Util.readInput(2019, 22, 1);
        List<IntUnaryOperator> operators = new ArrayList<>();
        for (String line : input) {
            String[] words = line.split(" ");
            if (words[0].equals("cut")) {
                operators.add(pos -> (pos + NUM_CARDS[0] - Integer.parseInt(words[1])) % NUM_CARDS[0]);
            } else if (words[2].equals("increment")) {
                operators.add(pos -> pos * Integer.parseInt(words[3]) % NUM_CARDS[0]);
            } else {
                operators.add(pos -> NUM_CARDS[0] - pos - 1);
            }
        }

        for (int prime = 11; prime < 1000; prime += 2) {
            if (!isPrime(prime)) {
                continue;
            }
            NUM_CARDS[0] = prime;
            int pos = 0;
            int shuffle = 0;
            do {
                for (IntUnaryOperator op : operators) {
                    pos = op.applyAsInt(pos);
                }
                shuffle++;
            } while (pos != 0);
            System.out.println(prime + "\t" + shuffle + "\t" + (prime-1)/shuffle);
        }
    }


    @Test
    @Disabled
    void increment3Loop() {
        List<Integer> tenList = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        List<Integer> iterList = new ArrayList<>(tenList);
        int loops = 0;
        System.out.println(formatList(iterList));
        do {
            List<Integer> tempList = new ArrayList<>(iterList);
            for (int i = 0; i < iterList.size(); i++) {
                int newIndex = i * 3 % tempList.size();
                tempList.set(newIndex, iterList.get(i));
            }
            loops++;
            iterList = tempList;
            System.out.println(formatList(iterList));
        } while (!tenList.equals(iterList));
        System.out.println("loops after " + loops);
    }

    String formatList(List<Integer> list) {
        return "[" + list.stream().map(i -> String.format("%2d", i)).collect(Collectors.joining(", ")) + "]";
    }

    @Test
    @Disabled
    void looptest() {
        long listSize = 119_315_717_514_047L;
        long factor = 64;
        long num = 1;
        int loops = 0;
        do {
            num *= factor;
            num %= listSize;
            loops++;
            Timer.tick(loops + " loops, num=" + num);
        } while (num != 1);
        System.out.println(loops);
    }
}
