package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day25 extends AbstractProblem<List<String>, Integer> {
   
    public static void main(String[] args) {
        new Day25().setAndSolve(Util.readInput(2018, 25));
    }
    
    @Override
    public Integer solve1() {
        int constellations = 0;
        List<Star> stars = input.stream().map(Star::new).collect(Collectors.toList());
        while (!stars.isEmpty()) {
            Collections.sort(stars);
            Star currentStar = stars.remove(0);
            if (currentStar.constellation == -1) {
                currentStar.constellation = ++constellations;
            }
            for (Star otherStar : stars) {
                if (currentStar.distanceTo(otherStar) <= 3) {
                    otherStar.constellation = currentStar.constellation;
                }
            }
        }
        return constellations;
    }
    
    
    private static class Star implements Comparable<Star> {
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
        
        @Override
        public int compareTo(Star other) {
            return other.constellation - constellation;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Star && constellation == ((Star) o).constellation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(constellation);
        }
    }
}
