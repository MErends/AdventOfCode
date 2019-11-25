package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 extends AbstractProblem<List<String>, Integer> {

    private List<Group> groups;
    private Map<Group, Group> targetMap;
    private List<Group> immunes;
    private List<Group> infections;

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2018, 24));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        input.remove(0);
        boolean part1done = false;
        int boost = 0;
        while (true) {
            loadGroups(boost);
            while (groups.stream().anyMatch(g -> g.army == Army.IMMUNE)
                    && groups.stream().anyMatch(g -> g.army == Army.INFECTION)) {
                int totalUnits = groups.stream().mapToInt(g -> g.unitCount).sum();
                groups.sort(Group.targetingComparator);
                immunes = groups.stream().filter(g -> g.army == Army.IMMUNE).collect(Collectors.toList());
                infections = groups.stream().filter(g -> g.army == Army.INFECTION).collect(Collectors.toList());
                fillTargetMap();
                groups.sort(Group.attackingComparator);
                doAttacks();
                if (totalUnits == groups.stream().mapToInt(g -> g.unitCount).sum()) {
                    break;
                }
                groups.removeIf(g -> g.unitCount == 0);
            }
            if (!part1done) {
                answer1 = groups.stream().mapToInt(g -> g.unitCount).sum();
                part1done = true;
            }
            if (groups.stream().allMatch(g -> g.army == Army.IMMUNE)) {
                answer2 = groups.stream().mapToInt(g -> g.unitCount).sum();
                return answer1;
            }
            boost++;
        }
    }

    private void doAttacks() {
        for (Group attacker : groups) {
            Group defender = targetMap.get(attacker);
            if (defender != null) {
                defender.beAttackedBy(attacker);
            }
        }
    }

    private void fillTargetMap() {
        targetMap = new HashMap<>();
        for (Group attacker : groups) {
            List<Group> targets = attacker.army == Army.INFECTION ? immunes : infections;
            Group target = findTarget(attacker, targets);
            targetMap.put(attacker, target);
            targets.remove(target);
        }
    }

    private void loadGroups(int boost) {
        groups = new ArrayList<>();
        boolean readingInfections = false;
        for (String line : input) {
            if (line.contains("Infection:")) {
                readingInfections = true;
            } else if (!line.isEmpty()) {
                groups.add(new Group(line, readingInfections ? Army.INFECTION : Army.IMMUNE, boost));
            }
        }
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
            int index = 7;
            while (true) {
                String word = words[index];
                if (word.contains("immune")) {
                    readingImmune = true;
                    readingWeakness = false;
                    index++;
                } else if (word.contains("weak")) {
                    readingImmune = false;
                    readingWeakness = true;
                    index++;
                } else if (word.contains("with")) {
                    break;
                } else if (readingImmune) {
                    immuneTo.add(Type.getType(word.substring(0, word.length() - 1)));
                }
                else if (readingWeakness) {
                    weakTo.add(Type.getType(word.substring(0, word.length() - 1)));
                }
                index++;
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
    
    private Group findTarget(Group attacker, List<Group> targets) {
        Group target = null;
        int largestDamage = 0;
        int targetEffectivePower = 0;
        int targetInitiative = 0;
        for (Group defender : targets) {
            if (potentialDamage(attacker, defender) > largestDamage
                    || (potentialDamage(attacker, defender) == largestDamage
                        && defender.getEffectivePower() > targetEffectivePower)
                    || (potentialDamage(attacker, defender) == largestDamage
                        && defender.getEffectivePower() == targetEffectivePower
                        && defender.initiative > targetInitiative)) {
                target = defender;
                largestDamage = potentialDamage(attacker, defender);
                targetEffectivePower = defender.getEffectivePower();
                targetInitiative = defender.initiative;
            }
        }
        return target;
    }
}