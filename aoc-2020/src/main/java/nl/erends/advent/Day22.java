package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day22 extends AbstractProblem<List<String>, Number> {

    static void main() {
        new Day22().setAndSolve(Util.readInput(2020, 22));
    }

    @Override
    public Integer solve1() {
        long splitIndex = input.indexOf("");

        List<Integer> player1 = input.stream()
                .skip(1)
                .limit(splitIndex - 1)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Integer> player2 = input.stream()
                .skip(splitIndex + 2)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        int winner = findWinner(player1, player2, part2);
        List<Integer> winList = new ArrayList<>(winner == 2 ? player2 : player1);
        final List<Integer> reverseList = winList.reversed();
        return IntStream.rangeClosed(1, reverseList.size())
                .reduce(0, (i1, i2) -> i1 + (i2 * reverseList.get(i2 - 1)));
    }

    private int findWinner(List<Integer> player1, List<Integer> player2, boolean recursive) {
        List<Integer> seenStates = new ArrayList<>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            int hashCode = player1.hashCode() + player2.hashCode();
            if (seenStates.contains(hashCode)) {
                return 1;
            } else {
                seenStates.add(hashCode);
            }
            int p1 = player1.removeFirst();
            int p2 = player2.removeFirst();
            int subwinner = (p1 > p2) ? 1 : 2;
            if (recursive && p1 <= player1.size() && p2 <= player2.size()) {
                subwinner = findWinner(new ArrayList<>(player1.subList(0, p1)),
                                       new ArrayList<>(player2.subList(0, p2)), true);
            }
            if (subwinner == 1) {
                player1.add(p1);
                player1.add(p2);
            } else {
                player2.add(p2);
                player2.add(p1);
            }
        }
        return player1.isEmpty() ? 2 : 1;
    }
}
