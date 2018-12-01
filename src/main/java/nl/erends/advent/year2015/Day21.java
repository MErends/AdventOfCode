package nl.erends.advent.year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Day21 {
    public static void main(String [] args) {
        int minimalCost = Integer.MAX_VALUE;
        int maximalCost = Integer.MIN_VALUE;
        int bossDamage = 8;
        int bossArmor = 1;
        List<Weapon> weapons = loadWeapons();
        List<Armor> armors = loadArmors();
        List<RingSet> ringSets = loadRingsets();
        for (Weapon weapon : weapons) {
            for (Armor armor : armors) {
                for (RingSet ringSet : ringSets) {
                    int myHP = 100;
                    int bossHP = 104;
                    int myDamage = weapon.damage + ringSet.damage;
                    int myArmor = armor.armor + ringSet.armor;
                    while (true) {
                        bossHP -= Math.max(1, myDamage - bossArmor);
                        if (bossHP <= 0) break;
                        myHP -= Math.max(1, bossDamage - myArmor);
                        if (myHP <= 0) break;
                    }
                    if (myHP > 0) {
                        minimalCost = Math.min(minimalCost, weapon.cost + armor.cost + ringSet.cost);
                    }
                    if (bossHP > 0) {
                        maximalCost = Math.max(maximalCost, weapon.cost + armor.cost + ringSet.cost);
                    }
                }
            }
        }
        System.out.println(minimalCost);
        System.out.println(maximalCost);
    }
    
    static List<Weapon> loadWeapons() {
        List<Weapon> list = new ArrayList<>();
        list.add(new Weapon(8, 4));
        list.add(new Weapon(10 , 5));
        list.add(new Weapon(25 , 6));
        list.add(new Weapon(40 , 7));
        list.add(new Weapon(74 , 8));
        return list;
    }
    
    static List<Armor> loadArmors() {
        List<Armor> list = new ArrayList<>();
        list.add(new Armor(0, 0));
        list.add(new Armor(13, 1));
        list.add(new Armor(31, 2));
        list.add(new Armor(53, 3));
        list.add(new Armor(75, 4));
        list.add(new Armor(102, 5));
        return list;
    }
    
    static List<Ring> loadRings() {
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
    
    static List<RingSet> loadRingsets() {
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
}


class Weapon {
    int cost;
    int damage;

    public Weapon(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }
}

class Armor {
    int cost;
    int armor;
    
    public Armor(int cost, int armor) {
        this.cost = cost;
        this.armor = armor;
    }

}

class Ring {
    int cost;
    int damage;
    int armor;

    public Ring(int cost, int damage, int armor) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

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

class RingSet {
    int cost;
    int damage;
    int  armor;
    Ring ring1;
    Ring ring2;

    public RingSet(Ring ring1, Ring ring2) {
        this.ring1 = ring1;
        this.ring2 = ring2;
        this.cost = ring1.cost + ring2.cost;
        this.damage = ring1.damage + ring2.damage;
        this.armor = ring1.armor + ring2.armor;
    }
    
    
}