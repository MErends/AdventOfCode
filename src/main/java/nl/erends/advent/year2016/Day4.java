package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day4 {

    public static void main(String[] args) {
        int totalID = 0;
        List<String> input = FileIO.getFileAsList("2016day4.txt");
        for (String line : input) {
            Room room = new Room(line);
            if (Objects.equals(room.getChecksum(), room.calculateChecksum())) {
                totalID += room.getId();
                System.out.println(room.decrypt() + " " + room.getId());
            }

        }
        System.out.print(totalID);
//        System.out.println(solve1(input));
//        System.out.println(solve2(input));
    }
}

class Room {
    int id;
    String name = "";
    String checksum;
    String fullName;

    public Room(String input) {
        fullName = input.substring(0, input.lastIndexOf('-'));
        String[] words = input.split("-");
        for (int index = 0; index < words.length - 1; index++) {
            name += words[index];
        }
        this.id = Integer.parseInt(words[words.length - 1].substring(0, words[words.length - 1].indexOf('[')));
        this.checksum = words[words.length - 1].substring(words[words.length - 1].indexOf('[') + 1, words[words.length - 1].length() - 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String calculateChecksum() {
        String checksum = "";
        char[] letters = name.toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        for (char letter : letters) {
            if (counts.containsKey(letter)) {
                counts.put(letter, counts.get(letter) + 1);
            } else {
                counts.put(letter, 1);
            }
        }
        while (!counts.isEmpty() && checksum.length() < 5) {
            int highestCount = 0;
            for (int count : counts.values()) {
                highestCount = Math.max(count, highestCount);
            }
            char lowestChar = Character.MAX_VALUE;
            for (char letter : counts.keySet()) {
                if (counts.get(letter) == highestCount && letter < lowestChar) {
                    lowestChar = letter;
                }
            }
            checksum += lowestChar;
            counts.remove(lowestChar);
        }
        return checksum;
    }

    public String decrypt() {
        int increment = id % 26;
        String output = "";
        for (char letter : fullName.toCharArray()) {
            if (letter == '-') {
                output += ' ';
            } else {
                if (letter + increment > 'z') {
                    output += (char) (letter - (26 - increment));
                } else {
                    output += (char) (letter + increment);
                }
            }
        }
        return output;
    }
}