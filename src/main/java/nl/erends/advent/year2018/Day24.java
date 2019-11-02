package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 {
       
    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day24.txt");
        long mid = System.currentTimeMillis();
        long start = System.currentTimeMillis();
        input.remove(0);
        boolean part1done = false;
        int boost = 0;
        while (true) {
            List<Group> groups = new ArrayList<>();
            boolean readingInfections = false;
            for (String line : input) {
                if (line.contains("Infection:")) {
                    readingInfections = true;
                    continue;
                }
                if (line.isEmpty()) {
                    continue;
                }
                groups.add(new Group(line, readingInfections ? Army.INFECTION : Army.IMMUNE, boost));
            }
            while (groups.stream().anyMatch(g -> g.army == Army.IMMUNE)
                    && groups.stream().anyMatch(g -> g.army == Army.INFECTION)) {
                int totalUnits = groups.stream().mapToInt(g -> g.unitCount).sum();
                groups.sort(Group.targetingComparator);
                List<Group> immunes = groups.stream().filter(g -> g.army == Army.IMMUNE).collect(Collectors.toList());
                List<Group> infections = groups.stream().filter(g -> g.army == Army.INFECTION).collect(Collectors.toList());
                Map<Group, Group> targetMap = new HashMap<>();
                for (Group attacker : groups) {
                    List<Group> targets = attacker.army == Army.INFECTION ? immunes : infections;
                    Group target = findTarget(attacker, targets);
                    targetMap.put(attacker, target);
                    targets.remove(target);
                }
                groups.sort(Group.attackingComparator);
                for (Group attacker : groups) {
                    Group defender = targetMap.get(attacker);
                    if (defender != null) {
                        defender.beAttackedBy(attacker);
                    }
                }
                if (totalUnits == groups.stream().mapToInt(g -> g.unitCount).sum()) {
                    break;
                }
                groups.removeIf(g -> g.unitCount == 0);
            }
            if (!part1done) {
                mid = System.currentTimeMillis();
                System.out.println(groups.stream().mapToInt(g -> g.unitCount).sum());
                part1done = true;
            }
            if (groups.stream().allMatch(g -> g.army == Army.IMMUNE)) {
                System.out.println(groups.stream().mapToInt(g -> g.unitCount).sum());
                break;
            }
            boost++;
        }
        long end = System.currentTimeMillis();
        
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    private enum Type {
        RADIATION,
        BLUDGEONING,
        FIRE,
        SLASHING,
        COLD;
        
        public static Type getType(String input) {
            return valueOf(input.toUpperCase());
        }
    }
    
    private enum Army {
        INFECTION,
        IMMUNE
    }
    
    private static class Group {
        int unitCount;
        int unitHP;
        List<Type> immuneTo = new ArrayList<>();
        List<Type> weakTo = new ArrayList<>();
        Type attackType;
        int unitAttack;
        int initiative;
        Army army;
        
        private Group(String input, Army army, int boost) {
            this.army = army;
            String[] words = input.split(" ");
            unitCount = Integer.parseInt(words[0]);
            unitHP = Integer.parseInt(words[4]);
            initiative = Integer.parseInt(words[words.length - 1]);
            attackType = Type.getType(words[words.length - 5]);
            unitAttack = Integer.parseInt(words[words.length - 6]) + (army == Army.IMMUNE ? boost : 0);
            boolean readingWeakness = false;
            boolean readingImmune = false;
            for (int index = 7; ; index++) {
                String word = words[index];
                if (word.contains("immune")) {
                    readingImmune = true;
                    readingWeakness = false;
                    index++;
                    continue;
                }
                if (word.contains("weak")) {
                    readingImmune = false;
                    readingWeakness = true;
                    index++;
                    continue;
                }
                if (word.contains("with")) {
                    break;
                }
                if (readingImmune) {
                    immuneTo.add(Type.getType(word.substring(0, word.length() - 1)));
                }
                if (readingWeakness) {
                    weakTo.add(Type.getType(word.substring(0, word.length() - 1)));
                }
            }
        }
        
        private void beAttackedBy(Group attacker) {
            int potentialDamage = potentialDamage(attacker, this);
            int unitsKilled = Math.min(potentialDamage / unitHP, unitCount);
            unitCount -= unitsKilled;
        }
        
        static Comparator<Group> targetingComparator = (g1, g2) -> {
            int effective1 = g1.getEffectivePower();
            int effective2 = g2.getEffectivePower();
            if (effective1 != effective2) return Integer.compare(effective2, effective1);
            return Integer.compare(g2.initiative, g1.initiative);
        };
        
        static Comparator<Group> attackingComparator = (g1, g2) -> Integer.compare(g2.initiative, g1.initiative);
        
        private int getEffectivePower() {
            return unitCount * unitAttack;
        }
    }
    
    private static int potentialDamage(Group attacker, Group defender) {
        if (defender.immuneTo.contains(attacker.attackType)) return -1;
        int multiplier = defender.weakTo.contains(attacker.attackType) ? 2 : 1;
        return multiplier * attacker.getEffectivePower();
    }
    
    private static Group findTarget(Group attacker, List<Group> targets) {
        Group target = null;
        int largestDamage = 0;
        int targetEffectivePower = 0;
        int targetInitiative = 0;
        for (Group defender : targets) {
            int potentialDamage = potentialDamage(attacker, defender);
            if (potentialDamage < largestDamage) continue;
            if (potentialDamage == largestDamage) {
                if (defender.getEffectivePower() < targetEffectivePower) continue;
                if (defender.getEffectivePower() == targetEffectivePower) {
                    if (defender.initiative < targetInitiative) continue;
                }
            }
            target = defender;
            largestDamage = potentialDamage;
            targetEffectivePower = defender.getEffectivePower();
            targetInitiative = defender.initiative;
        }
        return target;
    }
}