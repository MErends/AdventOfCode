package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Day21 extends AbstractProblem<String, Integer> {
    
    public static void main(String[] args) {
        new Day21().solve();
    }
    
    @Override
    public Integer solve1() {
        int minimalCost = Integer.MAX_VALUE;
        int maximalCost = Integer.MIN_VALUE;
        List<Weapon> weapons = loadWeapons();
        List<Armor> armors = loadArmors();
        List<RingSet> ringSets = loadRingsets();
        for (Weapon weapon : weapons) {
            for (Armor armor : armors) {
                for (RingSet ringSet : ringSets) {
                    if (resolveBattle(weapon, armor, ringSet)) {
                        minimalCost = Math.min(minimalCost, weapon.cost + armor.cost + ringSet.cost);
                    } else {
                        maximalCost = Math.max(maximalCost, weapon.cost + armor.cost + ringSet.cost);
                    }
                }
            }
        }
        answer2 = maximalCost;
        return minimalCost;
    }
    
    private boolean resolveBattle(Weapon weapon, Armor armor, RingSet ringSet) {
        int bossDamage = 8;
        int bossArmor = 1;
        int myHP = 100;
        int bossHP = 104;
        int myDamage = weapon.damage + ringSet.damage;
        int myArmor = armor.defense + ringSet.armor;
        while (true) {
            bossHP -= Math.max(1, myDamage - bossArmor);
            if (bossHP <= 0) return true;
            myHP -= Math.max(1, bossDamage - myArmor);
            if (myHP <= 0) return false;
        }
    }

    private List<Weapon> loadWeapons() {
        List<Weapon> list = new ArrayList<>();
        list.add(new Weapon(8, 4));
        list.add(new Weapon(10, 5));
        list.add(new Weapon(25, 6));
        list.add(new Weapon(40, 7));
        list.add(new Weapon(74, 8));
        return list;
    }

    private List<Armor> loadArmors() {
        List<Armor> list = new ArrayList<>();
        list.add(new Armor(0, 0));
        list.add(new Armor(13, 1));
        list.add(new Armor(31, 2));
        list.add(new Armor(53, 3));
        list.add(new Armor(75, 4));
        list.add(new Armor(102, 5));
        return list;
    }

    private List<Ring> loadRings() {
        List<Ring> list = new ArrayList<>();
        list.add(new Ring(0, 0, 0));
        list.add(new Ring(25, 1, 0));
        list.add(new Ring(50, 2, 0));
        list.add(new Ring(100, 3, 0));
        list.add(new Ring(20, 0, 1));
        list.add(new Ring(40, 0, 2));
        list.add(new Ring(80, 0, 3));
        return list;
    }

    private List<RingSet> loadRingsets() {
        List<RingSet> list = new ArrayList<>();
        List<Ring> rings1 = loadRings();
        List<Ring> rings2 = loadRings();
        list.add(new RingSet(rings1.get(0), rings2.get(0)));
        for (Ring ring1 : rings1) {
            rings2.remove(ring1);
            for (Ring ring2 : rings2) {
                list.add(new RingSet(ring1, ring2));
            }
        }
        return list;
    }


    private record Weapon(int cost, int damage) {
    }

    private record Armor(int cost, int defense) {

    }

    private record Ring(int cost, int damage, int armor) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ring ring = (Ring) o;
            return cost == ring.cost &&
                    damage == ring.damage &&
                    armor == ring.armor;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost, damage, armor);
        }
    }

    private static class RingSet {
        final int cost;
        final int damage;
        final int armor;
        final Ring ring1;
        final Ring ring2;

        RingSet(Ring ring1, Ring ring2) {
            this.ring1 = ring1;
            this.ring2 = ring2;
            this.cost = ring1.cost + ring2.cost;
            this.damage = ring1.damage + ring2.damage;
            this.armor = ring1.armor + ring2.armor;
        }
    }
}
