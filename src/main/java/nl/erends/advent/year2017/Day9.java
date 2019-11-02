package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;

public class Day9 {

    public static void main(String[] args) {
        char[] tokens = Util.getFileAsString("2017day9.txt").toCharArray();
        int depth = 0;
        int score = 0;
        int cancelled = 0;
        boolean inGarbage = false;
        for (int index = 0; index < tokens.length; index++) {
            char token = tokens[index];
            if (inGarbage && token != '>' && token != '!') {
                cancelled++;
                continue;
            }
            switch (token) {
                case '{':
                    depth++;
                    break;
                case '}':
                    score += depth;
                    depth--;
                    break;
                case '!':
                    index++;
                    break;
                case '<':
                    inGarbage = true;
                    break;
                case '>':
                    inGarbage = false;
                    break;
            }
        }
        System.out.println(score);
        System.out.println(cancelled);
    }
}