package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day07 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day07().setAndSolve(Util.readInput(2020, 7));
    }

    @Override
    public Integer solve1() {
        Map<String, Bag> bagMap = new HashMap<>();
        for (String line : input) {
            String[] words = line.split(" contain ");
            if (words[1].contains(" no ")) continue;
            String parentBagName = words[0].substring(0, words[0].length() - 5);
            Bag parentBag = bagMap.computeIfAbsent(parentBagName, Bag::new);
            String[] childStrings = words[1].split(", ");
            for (String childString : childStrings) {
                int beginName = childString.indexOf(' ') + 1;
                int endName = childString.indexOf("bag") - 1;
                String childBagName = childString.substring(beginName, endName);
                Bag childBag = bagMap.computeIfAbsent(childBagName, Bag::new);
                childBag.parents.add(parentBag);
            }
        }
        return bagMap.get("shiny gold").getAllParents().size();
    }
    
    @Override
    public Integer solve2() {
        return getWeight("shiny gold", new HashMap<>()) - 1;
        
    }
    
    private Integer getWeight(String name, Map<String, Integer> bagWeightMap) {
        if (bagWeightMap.containsKey(name)) return bagWeightMap.get(name);
        String line = getLine(name);
        if (line.contains("no ")) {
            bagWeightMap.put(name, 1);
            return 1;
        }
        int totalWeight = 1;
        String[] childStrings = line.split(", ");
        for (String childString : childStrings) {
            int beginName = childString.indexOf(' ') + 1;
            int endName = childString.indexOf("bag") - 1;
            int multiplier = Integer.parseInt(childString.substring(0, beginName - 1));
            String childBagName = childString.substring(beginName, endName);
            totalWeight += multiplier * getWeight(childBagName, bagWeightMap);
        }
        bagWeightMap.put(name, totalWeight);
        return totalWeight;
    }
    
    private String getLine(String name) {
        for (String line : input) {
            if (line.startsWith(name)) {
                return line.split("contain ")[1];
            }
        }
        throw new IllegalArgumentException(name + " not fount in input");
    }

    
    // muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
    // bright white bags contain 1 shiny gold bag.
    // faded blue bags contain no other bags.

    private static class Bag {
        final Set<Bag> parents = new HashSet<>();

        public Bag(String name) {
        }

        private Set<Bag> getAllParents() {
            Set<Bag> recusiveSet = new HashSet<>(parents);
            for (Bag parent : parents) {
                recusiveSet.addAll(parent.getAllParents());
            }
            return recusiveSet;
        }
    }
}
