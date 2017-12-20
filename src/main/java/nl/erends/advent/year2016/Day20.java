package nl.erends.advent.year2016;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Day20 {

    static List<String> blacklist = FileIO.getFileAsList("2016day20.txt");
    // 14975795
    public static void main(String[] args) {
       System.out.println(nextFree(0));
       int numFree = 0;
       long pointer = 0;
       while (true) {
           long freeOne = nextFree(pointer);
           if (freeOne == 4294967296L) break;
           long takenOne = nextBlocked(freeOne);
           numFree += (takenOne - freeOne);
           pointer = takenOne;
       }
       System.out.println(numFree);
    }
    
    
    
    public static long nextFree(long number) {
        long nextFree = number + 1;
        boolean done = false;
        while (!done) {
            done = true;
            int i = 0;
            for (String blackRange : blacklist) {
                long lower = Long.parseLong(blackRange.split("-")[0]);
                long upper = Long.parseLong(blackRange.split("-")[1]);
                if (nextFree >= lower && nextFree < upper) {
                    nextFree = Math.max(nextFree, upper + 1);
                    done = false;
                }
            }
        }
        return nextFree;
    }
    
    public static long nextBlocked(long number) {
        long nextBlocked = Long.MAX_VALUE;
        boolean done = false;
        for (String blackRange : blacklist) {
            long lower = Long.parseLong(blackRange.split("-")[0]);
            if (lower > number) {
                nextBlocked = Math.min(nextBlocked, lower);
            }
        }
        return nextBlocked;
    }
}
