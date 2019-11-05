package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day03 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readLine(2015, 3));
    }

    @Override
    public Integer solve1() {
        Santa santa = new Santa();
        List<House> houses = new ArrayList<>();
        addHouse(santa, houses);
        char[] movements = input.toCharArray();
        for (char movement : movements) {
            santa.doMovement(movement);
            addHouse(santa, houses);
        }
        return houses.size();
    }

    @Override
    public Integer solve2() {
        List<House> houses = new ArrayList<>();
        Santa santa = new Santa();
        Santa roboSanta = new Santa();
        addHouse(santa, houses);
        addHouse(roboSanta, houses);
        char[] movements = input.toCharArray();
        boolean santaTurn = true;
        for (char movement : movements) {
            if (santaTurn) {
                santa.doMovement(movement);
                addHouse(santa, houses);
            } else {
                roboSanta.doMovement(movement);
                addHouse(roboSanta, houses);
            }
            santaTurn = !santaTurn;
        }
        return houses.size();
    }


    private void addHouse(Santa santa, List<House> houses) {
        for (House house : houses) {
            if (house.x == santa.x && house.y == santa.y) {
                return;
            }
        }
        House house = new House(santa.x, santa.y);
        houses.add(house);
    }


    private class House {
        private int x;
        private int y;

        House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private class Santa {
        private int x;
        private int y;
        
        void doMovement(char movement) {
            switch (movement) {
                case '<':
                    x--;
                    break;
                case '^':
                    y++;
                    break;
                case '>':
                    x++;
                    break;
                case 'v':
                    y--;
                    break;
                default:
                    throw new IllegalArgumentException(movement + " is an illegal movement");
            }
        }
    }
}
