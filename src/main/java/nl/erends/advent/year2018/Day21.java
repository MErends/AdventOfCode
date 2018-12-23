package nl.erends.advent.year2018;

import java.util.HashSet;
import java.util.Set;

public class Day21 {
    
    public static void main(String[] args) {
        long mid = 0;
        long start = System.currentTimeMillis();
        boolean part1Done = false;
        long previousD = 0;
        Set<Long> dSet = new HashSet<>();

        long a = 0;
        long b;
        long c;
        long d = 0;
        long e;
        
        outer:
        do {
            c = d | 65536;
            d = 832312;
            while(true) {
                b = 255 & c;
                d += b;
                d = d & 16777215;
                d *= 65899;
                d = d & 16777215;
                if (256 > c) {
                    if (!part1Done) {
                        mid = System.currentTimeMillis();
                        System.out.println(d);
                        part1Done = true;
                    }
                    boolean added = dSet.add(d);
                    if (!added) {
                        System.out.println(previousD);
                        break outer;
                    }
                    previousD = d;
                    continue outer;
                }
                b = 0;
                while (true) {
                    e = b + 1;
                    e *= 256;
                    if (e > c) {
                        break;
                    }
                    b++;
                }
                c = b;
            }
        } while (a != d);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
}
