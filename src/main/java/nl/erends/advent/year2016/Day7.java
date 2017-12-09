package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    public static void main(String[] args) {
        List<String> lines = FileIO.getFileAsList("2016day7.txt");
        int tlsLines = 0;
        int sslLines = 0;
        for (String line : lines) {
            List<String> outsides = new ArrayList<>();
            List<String> insides = new ArrayList<>();
            while (line.contains("[")) {
                int split = line.indexOf('[');
                outsides.add(line.substring(0, split));
                line = line.substring(split + 1);
                split = line.indexOf("]");
                insides.add(line.substring(0, split));
                line = line.substring(split + 1);
            }
            outsides.add(line);
            if (supportsTLS(insides, outsides)) {
                tlsLines++;
            }
            if (supportsSSL(insides, outsides)) {
                sslLines++;
            }
        }
        System.out.println(tlsLines);
        System.out.println(sslLines);
    }


    public static boolean containsAbba(String input) {
        char[] letters = input.toCharArray();
        for (int index = 0; index < letters.length - 3; index++) {
            char a = letters[index];
            char b = letters[index + 1];
            if (a != b && letters[index + 2] == b && letters[index + 3] == a) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportsTLS(List<String> insides, List<String> outsides) {
        for (String inside : insides) {
            if (containsAbba(inside)) {
                return false;
            }
        }
        for (String outside : outsides) {
            if (containsAbba(outside)) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportsSSL(List<String> insides, List<String> outsides) {
        for (String inside : insides) {
            char[] letters = inside.toCharArray();
            for (int index = 0; index < letters.length - 2; index++) {
                char a = letters[index];
                char b = letters[index + 1];
                if (a != b && letters[index + 2] == a) {
                    String search = "" + b + a + b;
                    for (String outside : outsides) {
                        if (outside.contains(search)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
