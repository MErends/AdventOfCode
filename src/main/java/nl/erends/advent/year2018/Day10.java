package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day10.txt");
        long start = System.currentTimeMillis();
        Sky sky = new Sky(input);
        int ticks = 0;
        while (sky.getMaxY() - sky.getMinY() != 9) {
            sky.doTick();
            ticks++;
        }
        System.out.println(sky);
        System.out.println(ticks);
        long end = System.currentTimeMillis();
        System.out.println("Part 1 and 2: " + (end - start) + " millis.");
    }
    
    private static class Sky {
        private List<Star> stars;
        
        private Sky(List<String> input) {
            stars = new ArrayList<>();
            for (String line : input) {
                int x = Integer.parseInt(line.split("<")[1].split(",")[0].trim());
                int y = Integer.parseInt(line.split(",")[1].split(">")[0].trim());
                int vx = Integer.parseInt(line.split("<")[2].split(",")[0].trim());
                int vy = Integer.parseInt(line.split(",")[2].split(">")[0].trim());
                stars.add(new Star(x, y, vx, vy));
            }
        }
        
        void doTick() {
            stars.forEach(Star::doTick);
        }
        
        int getMaxX() {
            int maxX = Integer.MIN_VALUE;
            for (Star star : stars) {
                maxX = Integer.max(maxX, star.x);
            }
            return maxX;
        }

        int getMinX() {
            int minX = Integer.MAX_VALUE;
            for (Star star : stars) {
                minX = Integer.min(minX, star.x);
            }
            return minX;
        }

        int getMaxY() {
            int maxY = Integer.MIN_VALUE;
            for (Star star : stars) {
                maxY = Integer.max(maxY, star.y);
            }
            return maxY;
        }

        int getMinY() {
            int minY = Integer.MAX_VALUE;
            for (Star star : stars) {
                minY = Integer.min(minY, star.y);
            }
            return minY;
        }
        
        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            for (int y = getMinY(); y <= getMaxY(); y++) {
                for (int x = getMinX(); x <= getMaxX(); x++) {
                    final int xF = x;
                    final int yF = y;
                    out.append(stars.stream().anyMatch(s -> xF == s.x && yF == s.y) ? '#' : '.');
                }
                out.append('\n');
            }
            return out.toString();
        }
    }
    
    private static class Star {
        private int x;
        private int y;
        private int vx;
        private int vy;

        Star(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }
        
        public void doTick() {
            x += vx;
            y += vy;
        }
    }
}
