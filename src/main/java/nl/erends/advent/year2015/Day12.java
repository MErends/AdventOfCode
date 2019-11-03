package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day12 {
    public static void main(String[] args) {
        int sum = 0;
        char[] line = Util.readLine(2015, 12).toCharArray();
        Set<Character> allowed = new HashSet<>();
        allowed.add('0');
        allowed.add('1');
        allowed.add('2');
        allowed.add('3');
        allowed.add('4');
        allowed.add('5');
        allowed.add('6');
        allowed.add('7');
        allowed.add('8');
        allowed.add('9');
        allowed.add('-');
        for (int i = 0; i < line.length; i++) {
            if (!allowed.contains(line[i])) {
                line[i] = ' ';
            }
        }
        String lineString = new String(line);
        String[] words = lineString.split(" ");
        for (String word : words) {
            if (!word.equals("")) {
                sum += Integer.parseInt(word);
            }
        }
        System.out.println(sum);

    }
}
