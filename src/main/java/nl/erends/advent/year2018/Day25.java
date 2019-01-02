package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day25 {
   
    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day25_test.txt");
        long start = System.currentTimeMillis();
        int constellations = 0;
        List<Star> stars = input.stream().map(Star::new).collect(Collectors.toList());
        long mid = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    
    private static class Star {
        int x;
        int y;
        int z;
        int t;
        
        int constellation = -1;
        
        private Star(String input) {
            String[] words = input.split(",");
            x = Integer.parseInt(words[0]);
            y = Integer.parseInt(words[1]);
            z = Integer.parseInt(words[2]);
            t = Integer.parseInt(words[3]);
        }
        
        private int distanceTo(Star other) {
            return Math.abs(x - other.x)
                    + Math.abs(y - other.y)
                    + Math.abs(z - other.z)
                    + Math.abs(t - other.t);
        }
    }
}
