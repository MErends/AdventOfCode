package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day25 extends AbstractProblem<String, Integer> {

    private static boolean interactive = false;
    private static final String SOUTH = "south";
    private static final String NORTH = "north";

    public static void main(String[] args) {
        interactive = args.length > 0 && "-i".equals(args[0]);
        new Day25().setAndSolve(Util.readLine(2019, 25));
    }

    @Override
    public Integer solve1() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        Intcode computer = new Intcode(input);
        List<String> solution = Arrays.asList(SOUTH,
                "take astronaut ice cream",
                NORTH,
                "east",
                "take mouse",
                NORTH,
                NORTH,
                "east",
                "take sand",
                "west",
                SOUTH,
                SOUTH,
                SOUTH,
                "west",
                "take boulder",
                SOUTH,
                SOUTH,
                SOUTH,
                "west",
                SOUTH,
                SOUTH);
        int index = 0;
        while (!computer.isHalted()) {
            computer.execute();
            StringBuilder sb = new StringBuilder();
            while (computer.hasOutput()) {
                sb.append((char) computer.getOutput().longValue());
                computer.execute();
            }
            LOG.info(sb);
            Matcher m =  Pattern.compile("\\d+").matcher(sb);
            if (sb.toString().contains("keypad") && m.find()) {
                return Integer.parseInt(m.group());
            }
            String input = solution.get(index++);
            if (interactive) {
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    LOG.error("Couldn't read input!", e);
                    return null;
                }
            } else {
                LOG.info(input);
            }
            for (char c : input.toCharArray()) {
                computer.addInput(c);
            }
            computer.addInput('\n');
        }
        return null;
    }

    @Override
    public Integer solve2() {
        return null;
    }
}
