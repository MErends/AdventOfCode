package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {

    private static Map<String, Long> memoryMap = new HashMap<>();

    public static void main(String[] args) {
        int timesMultiplied = 0;
        List<String> commands = Util.getFileAsList("2017day23.txt");
        memoryMap.put("a", 0L);
        int pointer = 0;
        while (pointer >=0 && pointer < commands.size()) {
            String[] words = commands.get(pointer).split(" ");
            switch (words[0]) {
                case "set": {
                    setMemory(words[1], getMemory(words[2]));
                    break;
                }
                case "sub": {
                    long memory = getMemory(words[1]);
                    long multiply = getMemory(words[2]);
                    setMemory(words[1], memory  - multiply);
                    break;
                }
                case "mul": {
                    long memory = getMemory(words[1]);
                    long multiply = getMemory(words[2]);
                    setMemory(words[1], memory  * multiply);
                    timesMultiplied++;
                    break;
                }
                case "jnz": {
                    long memory = getMemory(words[1]);
                    long offset = getMemory(words[2]);
                    if (memory != 0) {
                        pointer += offset;
                        continue;
                    }
                    break;
                }
            }
            pointer++;
        }
        System.out.println(timesMultiplied);
        for (String key : memoryMap.keySet()) {
            System.out.println(key + ": " + memoryMap.get(key));
        }
        
        int solution = 0;
        for (int b = 107_900; b <= 124_900; b += 17) {
            if (!isPrime(b)) {
                solution++;
            }
        }
        System.out.println(solution);
        
        long a = 0;
        long b = 0;
        long c = 0;
        long d = 0;
        long e = 0;
        boolean f = true;
        long g = 0;
        long h = 0;

        b = 79;
        c = 79;
        b = 10;
        c = 27;
        if (a != 0) {
            b = 107_900;
            c = 124_900;
        }
        for ( ; b <= c; b += 17) {
            f = false;
            for (d = 2; d < b; d++) {
                for (e = 2; e < b; e++) {
                    if (d * e == b) {
                        f = true;
                    }
                }
            }
            if (f) {
                h++;
            }
        }

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
        System.out.println("e: " + e);
        System.out.println("f: " + f);
        System.out.println("g: " + g);
        System.out.println("h: " + h);

// 1000 too high







    }
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    private static void setMemory(String memory, long value) {
        memoryMap.put(memory, value);
    }

    private static Long getMemory(String memory) {
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

