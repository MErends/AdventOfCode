package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day04 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2016, 4));
    }

    @Override
    public Integer solve1() {
        int totalID = 0;
        for (String line : input) {
            Room room = new Room(line);
            if (Objects.equals(room.checksum, room.calculateChecksum())) {
                totalID += room.id;
                if ("northpole object storage".equals(room.decrypt())) {
                    answer2 = room.id;
                }
            }
        }
        return totalID;
    }

    private class Room {
        int id;
        StringBuilder name = new StringBuilder();
        String checksum;
        String fullName;

        Room(String input) {
            fullName = input.substring(0, input.lastIndexOf('-'));
            String[] words = input.split("-");
            for (int index = 0; index < words.length - 1; index++) {
                name.append(words[index]);
            }
            this.id = Integer.parseInt(words[words.length - 1].substring(0, words[words.length - 1].indexOf('[')));
            this.checksum = words[words.length - 1].substring(words[words.length - 1].indexOf('[') + 1, words[words.length - 1].length() - 1);
        }

        String calculateChecksum() {
            StringBuilder checksumSB = new StringBuilder();
            char[] letters = name.toString().toCharArray();
            Map<Character, Integer> counts = new HashMap<>();
            for (char letter : letters) {
                if (counts.containsKey(letter)) {
                    counts.put(letter, counts.get(letter) + 1);
                } else {
                    counts.put(letter, 1);
                }
            }
            while (!counts.isEmpty() && checksumSB.length() < 5) {
                int highestCount = 0;
                for (int count : counts.values()) {
                    highestCount = Math.max(count, highestCount);
                }
                char lowestChar = Character.MAX_VALUE;
                for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                    if (entry.getValue() == highestCount && entry.getKey() < lowestChar) {
                        lowestChar = entry.getKey();
                    }
                }
                checksumSB.append(lowestChar);
                counts.remove(lowestChar);
            }
            return checksumSB.toString();
        }

        String decrypt() {
            int increment = id % 26;
            StringBuilder output = new StringBuilder();
            for (char letter : fullName.toCharArray()) {
                if (letter == '-') {
                    output.append(' ');
                } else {
                    if (letter + increment > 'z') {
                        output.append((char) (letter - (26 - increment)));
                    } else {
                        output.append((char) (letter + increment));
                    }
                }
            }
            return output.toString();
        }
    }
}