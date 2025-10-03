package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day19 extends AbstractProblem<List<String>, Integer> {
       
    void main() {
        new Day19().setAndSolve(Util.readInput(2015, 19));
    }

    @Override
    public Integer solve1() {
        // replace Rn with (
        // replace Ar with )
        // replace Y  with ,
        // possible replacements are
        // X => YZ          will save 1 element
        // X => Y(Z)        saves 1 element per ( and )
        // X => Y(Z,U)      comma saves 2 elements
        // X => Y(Z,U,V)    each comma saves 2 elements
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
        line = line.replace("Rn", "(");
        line = line.replace("Ar", ")");
        line = line.replace("Y", ",");
        int parentheses = countOccurences(line, '(');
        parentheses += countOccurences(line, ')');
        int commas = countOccurences(line, ',');
        return totalLength - parentheses - (2 * commas) - 1;
    }
    
    private int countOccurences(String input, char token) {
        int count = 0;
        for (char c : input.toCharArray()) {
            count += c == token ? 1 : 0;
        }
        return count;
    }
}
