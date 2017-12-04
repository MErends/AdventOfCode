package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws Exception {
        int niceLine = 0;
        List<String> regels = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day5.txt");
//        regels.clear();
//        regels.add("uurcxstgmygtbstg");
        for (String regel : regels) {
            System.out.println("\nRegel: " + regel);
//            if (has3Vowels(regel) && hasDoubles(regel) && !hasBadWords(regel)) {
            if (hasDoubleString(regel) && hasSkips(regel)) {
                System.out.println("Nice!");
                niceLine++;
            } else {
                System.out.println("Not nice");
            }
        }
        System.out.print(niceLine + " in total");
    }

    private static boolean has3Vowels(String line) {
        String vowels = "aeiou";
        int numVowels = 0;
        for (int i = 0; i < line.length(); i++) {
            if (vowels.contains(line.substring(i, i + 1))) {
                numVowels++;
            }
            if (numVowels == 3) {
                System.out.println("Enough vowels");
                return true;
            }
        }
        System.out.println("Not enough vowels!");
        return false;
    }

    private static boolean hasDoubles(String line) {
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == line.charAt(i + 1)) {
                System.out.println("Has a double");
                return true;
            }
        }
        System.out.println("Has no doubles");
        return false;
    }

    private static boolean hasBadWords(String line) {
        List<String> badWords = new ArrayList<>();
        badWords.add("ab");
        badWords.add("cd");
        badWords.add("pq");
        badWords.add("xy");
        for (String badWord : badWords) {
            if (line.contains(badWord)) {
                System.out.println("Has bad word! " + badWord);
                return true;
            }
        }
        System.out.println("Has no bad words");
        return false;
    }

    private static boolean hasDoubleString(String line) {
        for (int i = 0; i < line.length() - 2; i++) {
            String searchterm = line.substring(i, i + 2);
            String subject = line.substring(i + 2);
            if (subject.contains(searchterm)) {
                System.out.println(searchterm + " found twice!");
                return true;
            }
        }
        System.out.println("No doubles found");
        return false;
    }

    private static boolean hasSkips(String line) {
        for (int i = 0; i < line.length() - 2; i++) {
            if (line.charAt(i) == line.charAt(i + 2)) {
                System.out.println(line.charAt(i) + " is skipping");
                return true;
            }
        }
        return false;
    }
}
