package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.*;
import java.util.stream.Collectors;

public class Day19 {
       
    public static void main(String[] args) {
        // replace Rn with (
        // replace Ar with )
        // replace Y  with ,
        // possible replacements are
        // X => YZ          will save 1 element
        // X => Y(Z)        saves 1 element per ( and )
        // X => Y(Z,U)      comma saves 2 elements
        // X => Y(Z,U,V)    each comma saves 2 elements
        List<String> input = FileIO.getFileAsList("2015day19.txt");
        String line = "";
        for (int index = 0; index < input.size(); index++) {
            if (input.get(index).isEmpty()) {
                line = input.get(index + 1);
                break;
            }
        }
        int totalLength = 0;
        for (char c : line.toCharArray()) {
            if (Character.isUpperCase(c)) totalLength++;
        }
        line = line.replaceAll("Rn", "(");
        line = line.replaceAll("Ar", ")");
        line = line.replaceAll("Y", ",");
        int parentheses = countOccurences(line, '(');
        parentheses += countOccurences(line, ')');
        int commas = countOccurences(line, ',');
        System.out.println(totalLength - parentheses - (2 * commas) - 1);
        
        // 354 too high
    }
    
    
    private static int countOccurences(String input, char token) {
        int count = 0;
        for (char c : input.toCharArray()) {
            count += c == token ? 1 : 0;
        }
        return count;
    }
}