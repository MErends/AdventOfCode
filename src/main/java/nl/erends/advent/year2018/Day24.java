package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day24 {
   
    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day24.txt");
        long mid = System.currentTimeMillis();
        long start = System.currentTimeMillis();
        input.remove(0);
        boolean part1done = false;
        for (int boost = 0; boost <= 100 ; boost++) {
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
                int totalUnits = groups.stream().mapToInt(group -> group.unitCount).sum();
                groups.sort(Group.targetingComparator);
                List<Group> immunes = new ArrayList<>(groups);
                immunes.removeIf(g -> g.army != Army.IMMUNE);
                List<Group> infections = new ArrayList<>(groups);
                infections.removeIf(g -> g.army != Army.INFECTION);
                Map<Group, Group> targetMap = new HashMap<>();
                for (Group attacker : groups) {
                    List<Group> targets = attacker.army == Army.INFECTION ? immunes : infections;
                    Group target = null;
                    int largestDamage = 0;
                    int targetEffectivePower = 0;
                    for (Group defender : targets) {
                        int potentialDamage = potentialDamage(attacker, defender);
                        int effectivePower = defender.unitCount * defender.unitAttack;
                        if (potentialDamage < largestDamage) continue;
                        if (potentialDamage == largestDamage) {
                            if (effectivePower < targetEffectivePower) continue;
                            if (effectivePower == targetEffectivePower) {
                                if (defender.initiative < target.initiative) continue;
                            }
                        }
                        target = defender;
                        largestDamage = potentialDamage;
                        targetEffectivePower = effectivePower;
                    }
                    if (largestDamage == 0) continue;
                    targetMap.put(attacker, target);
                    targets.remove(target);
                }
                groups.sort(Group.attackingComparator);
                for (Group attacker : groups) {
                    Group defender = targetMap.get(attacker);
                    if (defender != null) {
                        defender.getAttackedBy(attacker);
                    }
                }
                if (totalUnits == groups.stream().mapToInt(group -> group.unitCount).sum()) {
                    break;
                }
                groups.removeIf(group -> group.unitCount == 0);
            }
            if (!part1done) {
                mid = System.currentTimeMillis();
                System.out.println(groups.stream().mapToInt(g -> g.unitCount).sum());
                part1done = true;
            }
            if (groups.stream().allMatch(g -> g.army == Army.IMMUNE)) {
                System.out.println("Won with:\t" + boost); ///93,94,95,96
            } else if (groups.stream().allMatch(g -> g.army == Army.INFECTION)) {
                System.out.println("Lost with:\t" + boost);
            } else {
                System.out.println("Draw with:\t" + boost);
            }
        }
        long end = System.currentTimeMillis();
        
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    private enum Type {
        RADIATION,
        BLUDGEONING,
        FIRE,
        SLASHING,
        COLD
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
            attackType = Type.valueOf(words[words.length - 5].toUpperCase());
            unitAttack = Integer.parseInt(words[words.length - 6]) + (army == Army.IMMUNE ? boost : 0);
            boolean readingWeakness = false;
            boolean readingImmune = false;
            for (int index = 7; ; index++) {
                String word = words[index];
                if (word.contains("immune")) {
                    readingImmune = true;
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
                    immuneTo.add(Type.valueOf(word.substring(0, word.length() - 1).toUpperCase()));
                }
                if (readingWeakness) {
                    weakTo.add(Type.valueOf(word.substring(0, word.length() - 1).toUpperCase()));
                }
            }
        }
        
        private void getAttackedBy(Group attacker) {
            int potentialDamage = potentialDamage(attacker, this);
            int unitsKilled = potentialDamage / unitHP;
            unitCount -= unitsKilled;
            unitCount = Math.max(unitCount, 0);
        }
        
        static Comparator<Group> targetingComparator = (group1, group2) -> {
            int effective1 = group1.unitCount * group1.unitAttack;
            int effective2 = group2.unitCount * group2.unitAttack;
            if (effective1 != effective2) return Integer.compare(effective2, effective1);
            return Integer.compare(group2.initiative, group1.initiative);
        };
        
        static Comparator<Group> attackingComparator = (group1, group2) -> Integer.compare(group2.initiative, group1.initiative);
    }
    
    private static int potentialDamage(Group attack, Group defense) {
        if (defense.immuneTo.contains(attack.attackType)) return 0;
        int multiplier = defense.weakTo.contains(attack.attackType) ? 2 : 1;
        return multiplier * attack.unitCount * attack.unitAttack;
    }
}

//        Immune System:
//        17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2
//        989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3
//
//        Infection:
//        801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1
//        4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4