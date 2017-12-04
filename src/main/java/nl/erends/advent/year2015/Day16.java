package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) throws Exception {
        int sum = 0;

        List<String> lines = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day16.txt");
        List<Sue> sues = new ArrayList<>();
        for (String line : lines) {
            sues.add(new Sue(line));
        }

        Iterator<Sue> iterator = sues.iterator();
        while (iterator.hasNext()) {
            Sue sue = iterator.next();
            if (sue.getProperties().get("children") != null && !sue.getProperties().get("children").equals(3)) {
                iterator.remove();
                continue;
            }
//            if (sue.getProperties().get("cats") != null && !sue.getProperties().get("cats").equals(7)) {
            if (sue.getProperties().get("cats") != null && sue.getProperties().get("cats").compareTo(7) < 0) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("samoyeds") != null && !sue.getProperties().get("samoyeds").equals(2)) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("pomeranians") != null && sue.getProperties().get("pomeranians").compareTo(3) > 0) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("akitas") != null && !sue.getProperties().get("akitas").equals(0)) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("vizslas") != null && !sue.getProperties().get("vizslas").equals(0)) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("goldfish") != null && sue.getProperties().get("goldfish").compareTo(5) > 0) {
                iterator.remove();
                continue;
            }
//            if (sue.getProperties().get("trees") != null && !sue.getProperties().get("trees").equals(3)) {
            if (sue.getProperties().get("trees") != null && sue.getProperties().get("trees").compareTo(3) < 0) {

                    iterator.remove();
                continue;
            }
            if (sue.getProperties().get("cars") != null && !sue.getProperties().get("cars").equals(2)) {
                iterator.remove();
                continue;
            }
            if (sue.getProperties().get("perfumes") != null && !sue.getProperties().get("perfumes").equals(1)) {
                iterator.remove();
                continue;
            }
            System.out.println(sue.getNumber());
        }


        System.out.println(sues.get(0).getNumber());

    }
}


class Sue {
    int number;
    Map<String, Integer> properties;

    public Sue(String line) {
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

    public int getNumber() {
        return number;
    }

    public Map<String, Integer> getProperties() {
        return properties;
    }
}