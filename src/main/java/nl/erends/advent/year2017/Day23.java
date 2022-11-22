package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 extends AbstractProblem<List<String>, Integer> {

    private static final Map<String, Long> memoryMap = new HashMap<>();

    public static void main(String[] args) {
        new Day23().setAndSolve(Util.readInput(2017, 23));
    }
    
    @Override
    public Integer solve1() {
        int timesMultiplied = 0;
        memoryMap.put("a", 0L);
        int pointer = 0;
        while (pointer >= 0 && pointer < input.size()) {
            String[] words = input.get(pointer).split(" ");
            switch (words[0]) {
                case "set":
                    setMemory(words[1], getMemory(words[2]));
                    break;
                case "sub":
                    setMemory(words[1], getMemory(words[1]) - getMemory(words[2]));
                    break;
                case "mul":
                    setMemory(words[1], getMemory(words[1]) * getMemory(words[2]));
                    timesMultiplied++;
                    break;
                case "jnz":
                    if (getMemory(words[1]) != 0) {
                        pointer += getMemory(words[2]);
                        continue;
                    }
                    break;
                default:
            }
            pointer++;
        }
        return timesMultiplied;
    }
        
    @Override
    public Integer solve2() {
            int solution = 0;
            for (int b = 107_900; b <= 124_900; b += 17) {
                if (!isPrime(b)) {
                    solution++;
                }
            }
            return solution;
        }

        // b = 79
        // c = 79
        // b = 10
        // c = 27
        // if a != 0 
        //     b = 107_900
        //     c = 124_900
        // for ; b <= c ; b += 17
        //     f = false
        //     for d = 2; d < b; d++
        //         for e = 2; e < b; e++
        //             if d * e == b
        //                 f = true
        //     if f
        //         h++
    
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    private void setMemory(String memory, long value) {
        memoryMap.put(memory, value);
    }

    private Long getMemory(String memory) {
        try {
            return Long.parseLong(memory);
        } catch (Exception e){
            if (memoryMap.containsKey(memory)) {
                return memoryMap.get(memory);
            } else {
                memoryMap.put(memory, 0L);
                return 0L;
            }
        }
    }
}

