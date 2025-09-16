package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 19: Aplenty ---</h1>
 * <p>As you reach the bottom of the relentless avalanche of machine parts, you
 * discover that they're already forming a formidable heap. each part is sent
 * through a series of workflows that will ultimately accept or reject the part.
 * Each workflow has a name and contains a list of rules; each rule specifies a
 * condition and where to send the part if the condition is true. What do you
 * get if you add together all of the rating numbers for all of the parts that
 * ultimately get accepted?</p>
 * <p><a href="https://adventofcode.com/2023/day/19">2023 Day 19</a></p>
 */
public class Day19 extends AbstractProblem<List<String>, Number> {

    private final Map<String, Workflow> workflows = new HashMap<>();
    private final List<Cog> cogs = new ArrayList<>();
    private long cogsAccepted = 0;

    static void main() {
        new Day19().setAndSolve(Util.readInput(2023, 19));
    }

    @Override
    protected Number solve1() {
        boolean readingWorkflows = true;
        for (String line : input) {
            if (line.isBlank()) {
                readingWorkflows = false;
                continue;
            }
            if (readingWorkflows) {
                String[] lineSplit = line.split("\\{");
                workflows.put(lineSplit[0], new Workflow(lineSplit[1].substring(0, lineSplit[1].length() - 1)));
            } else {
                cogs.add(new Cog(line));
            }
        }
        return cogs.stream().filter(workflows.get("in")).flatMap(cog -> cog.values.values().stream()).mapToInt(i -> i).sum();
    }

    @Override
    public Number solve2() {
        new CogSpan().resolveSpan();
        return cogsAccepted;
    }

    private static class Cog {

        private static final Pattern LINE_PAT = Pattern.compile("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}");
        final HashMap<Character, Integer> values = new HashMap<>();

        private Cog(String line) {
            Matcher m = LINE_PAT.matcher(line);
            if (!m.find()) throw new IllegalStateException();
            values.put('x', Integer.parseInt(m.group(1)));
            values.put('m', Integer.parseInt(m.group(2)));
            values.put('a', Integer.parseInt(m.group(3)));
            values.put('s', Integer.parseInt(m.group(4)));
        }
    }

    private class Workflow implements Predicate<Cog> {

        final List<Rule> rules;

        public boolean test(Cog cog) {
            for (Rule rule : rules) {
                String result = rule.apply(cog);
                if (result == null) {
                    continue;
                } else if (result.length() > 1) {
                    return workflows.get(result).test(cog);
                }
                return result.equals("A");
            }
            throw new IllegalStateException();
        }

        private Workflow(String line) {
            rules = Arrays.stream(line.split(",")).map(Rule::new).toList();
        }
    }

    private static class Rule implements Function<Cog, String> {

        char property;
        Predicate<Integer> test;
        final String result;
        int testValue;
        char operator;

        @Override
        public String apply(Cog cog) {
            if (test != null) {
                if (test.test(cog.values.get(property))) {
                    return result;
                }
                return null;
            }
            return result;
        }

        private Rule(String line) {
            if (line.indexOf('>') != -1) {
                operator = '>';
                String[] lineSplit = line.split(":");
                property = line.charAt(0);
                testValue = Integer.parseInt(lineSplit[0].substring(2));
                test = i -> i > testValue;
                result = lineSplit[1];
            } else if (line.indexOf('<') != -1) {
                operator = '<';
                String[] lineSplit = line.split(":");
                property = line.charAt(0);
                testValue = Integer.parseInt(lineSplit[0].substring(2));
                test = i -> i < testValue;
                result = lineSplit[1];
            } else {
                result = line;
            }
        }
    }

    private class CogSpan {

        int lowX = 1;
        int highX = 4001;
        int lowM = 1;
        int highM = 4001;
        int lowA = 1;
        int highA = 4001;
        int lowS = 1;
        int highS = 4001;

        String workflowName = "in";
        int ruleNr = 0;

        private CogSpan() {
        }

        private CogSpan(CogSpan parent) {
            this.lowX = parent.lowX;
            this.highX = parent.highX;
            this.lowM = parent.lowM;
            this.highM = parent.highM;
            this.lowA = parent.lowA;
            this.highA = parent.highA;
            this.lowS = parent.lowS;
            this.highS = parent.highS;
            this.workflowName = parent.workflowName;
            this.ruleNr = parent.ruleNr;
        }

        private void resolveSpan() {
            if (workflowName.length() == 1) {
                if (workflowName.equals("A")) {
                    int xDif = highX - lowX;
                    int mDif = highM - lowM;
                    int aDif = highA - lowA;
                    int sDif = highS - lowS;
                    cogsAccepted += ((long) xDif) * mDif * aDif * sDif;
                }
                return;
            }
            Workflow workflow = workflows.get(workflowName);
            Rule rule = workflow.rules.get(ruleNr);
            if (rule.operator == '\0') {
                this.workflowName = rule.result;
                this.ruleNr = 0;
                this.resolveSpan();
                return;
            }
            CogSpan lower = new CogSpan(this);
            CogSpan higher = new CogSpan(this);
            int split = rule.testValue;
            if (rule.operator == '>') split++;
            switch (rule.property) {
                case 'x' -> {
                    lower.highX = split;
                    higher.lowX = split;
                }
                case 'm' -> {
                    lower.highM = split;
                    higher.lowM = split;
                }
                case 'a' -> {
                    lower.highA = split;
                    higher.lowA = split;
                }
                default -> {
                    lower.highS = split;
                    higher.lowS = split;
                }
            }
            if (rule.operator == '>') {
                lower.ruleNr++;
                higher.workflowName = rule.result;
                higher.ruleNr = 0;
            } else {
                higher.ruleNr++;
                lower.workflowName = rule.result;
                lower.ruleNr = 0;

            }
            lower.resolveSpan();
            higher.resolveSpan();
        }
    }
}


