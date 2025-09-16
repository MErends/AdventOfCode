package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>--- Day 2: Red-Nosed Reports ---</h1>
 * <p>Your puzzle input consists of many reports, one report per line. Each
 * report is a list of numbers called levels. Analyze the unusual data from the
 * engineers. How many reports are safe?</p>
 * <p><a href="https://adventofcode.com/2024/day/2">2024 Day 2</a></p>
 */
public class Day02 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day02().setAndSolve(Util.readInput(2024, 2));
    }

    @Override
    protected Integer solve1() {
        return (int) input.stream()
                .map(Report::new).filter(Report::isSafe).count();
    }

    @Override
    public Integer solve2() {
        return (int) input.stream()
                .map(Report::new).filter(Report::maybeSafe).count();
    }

    private static class Report {

        private List<Integer> levels;

        Report() {}

        Report(String line) {
            levels = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt).toList();
        }

        boolean isSafe() {
            boolean increases = true;
            boolean decreases = true;
            for (int index = 0; index < levels.size() - 1; index++) {
                int diff = levels.get(index) - levels.get(index + 1);
                if (increases && (diff > 3 || diff <= 0)) {
                    increases = false;
                }
                if (decreases && (diff < -3 || diff >= 0)) {
                    decreases = false;
                }
                if (!increases && !decreases) {
                    return false;
                }
            }
            return true;
        }

        boolean maybeSafe() {
            if (isSafe()) {
                return true;
            }
            for (int index = 0; index < levels.size(); index++) {
                List<Integer> cloneList = new ArrayList<>(levels);
                cloneList.remove(index);
                Report cloneReport = new Report();
                cloneReport.levels = cloneList;
                if (cloneReport.isSafe()) {
                    return true;
                }
            }
            return false;
        }
    }
}
