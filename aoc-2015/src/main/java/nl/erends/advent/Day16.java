package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readInput(2015, 16));
    }

    @Override
    public Integer solve1() {
        List<Sue> sues = new ArrayList<>();
        for (String line : input) {
            sues.add(new Sue(line));
        }
        for (Sue sue : sues) {
            sue.checkEquals("children", 3, true);
            sue.checkEquals("cats", 7, false);
            sue.checkIsMore("cats", 7);
            sue.checkEquals("samoyeds", 2, true);
            sue.checkEquals("pomeranians", 3, false);
            sue.checkIsLess("pomeranians", 3);
            sue.checkEquals("akitas", 0, true);
            sue.checkEquals("vizslas", 0, true);
            sue.checkEquals("goldfish", 5, false);
            sue.checkIsLess("goldfish", 5);
            sue.checkEquals("trees", 3, false);
            sue.checkIsMore("trees", 3);
            sue.checkEquals("cars", 2, true);
            sue.checkEquals("perfumes", 1, true);
        }
        answer2 = sues.stream().filter(sue -> sue.canBe2).mapToInt(Sue::getNumber).findFirst().orElseThrow(IllegalStateException::new);
        return sues.stream().filter(sue -> sue.canBe1).mapToInt(Sue::getNumber).findFirst().orElseThrow(IllegalStateException::new);
    }
    
    private static class Sue {
        private final int number;
        private final Map<String, Integer> properties;
        private boolean canBe1 = true;
        private boolean canBe2 = true;
    
        Sue(String line) {
            String[] words = line.split(" ");
            this.number = Integer.parseInt(words[1].substring(0, words[1].length() - 1));
            this.properties = new HashMap<>();
            String prop1 = words[2].substring(0, words[2].length() - 1);
            Integer count1 = Integer.parseInt(words[3].substring(0, words[3].length() - 1));
            String prop2 = words[4].substring(0, words[4].length() - 1);
            Integer count2 = Integer.parseInt(words[5].substring(0, words[5].length() - 1));
            String prop3 = words[6].substring(0, words[6].length() - 1);
            Integer count3 = Integer.parseInt(words[7]);
            properties.put(prop1, count1);
            properties.put(prop2, count2);
            properties.put(prop3, count3);
        }
    
        int getNumber() {
            return number;
        }
    
        int getProperty(String property) {
            return properties.getOrDefault(property, -1);
        }
        
        void checkEquals(String property, int value, boolean forBoth) {
            int actual = getProperty(property);
            if (actual != -1 && actual != value) {
                    canBe1 = false;
                if (forBoth) {
                    canBe2 = false;
                }
            }
        }

        void checkIsLess(String property, int value) {
            int actual = getProperty(property);
            if (actual != -1 && actual >= value) {
                canBe2 = false;
            }
        }
        
        void checkIsMore(String property, int value) {
            int actual = getProperty(property);
            if (actual != -1 && actual <= value) {
                canBe2 = false;
            }
        }
    }
}
