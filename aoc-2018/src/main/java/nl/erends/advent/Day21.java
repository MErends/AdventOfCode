package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.HashSet;
import java.util.Set;

public class Day21 extends AbstractProblem<String, Long> {
    
    public static void main(String[] args) {
        new Day21().setAndSolve(null);
    }
    
    @Override
    public Long solve1() { //NOSONAR
        long answer1 = 0;
        boolean part1Done = false;
        long previousD = 0;
        Set<Long> dSet = new HashSet<>();

        long a = 0;
        long b;
        long c;
        long d = 0;
        long e;
        
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
                        answer1 = d;
                        part1Done = true;
                    }
                    boolean added = dSet.add(d);
                    if (!added) {
                        answer2 = previousD;
                        return answer1;
                    }
                    previousD = d;
                    break;
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
        return 0L;
    }
}
