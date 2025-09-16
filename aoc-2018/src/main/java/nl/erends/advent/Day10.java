package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day10 extends AbstractProblem<List<String>, String> {
    
    private int fontSize = 9;

    static void main() {
        new Day10().setAndSolve(Util.readInput(2018, 10));
    }
    
    @Override
    public String solve1() {
        Sky sky = new Sky(input);
        int ticks = 0;
        while (sky.getMaxY() - sky.getMinY() > fontSize) {
            sky.doTick();
            ticks++;
        }
        answer2 = Integer.toString(ticks);
        return sky.toString();
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    private static class Sky {
        private final List<Star> stars;
        
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
        private final int vx;
        private final int vy;

        Star(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }
        
        void doTick() {
            x += vx;
            y += vy;
        }
    }
}
