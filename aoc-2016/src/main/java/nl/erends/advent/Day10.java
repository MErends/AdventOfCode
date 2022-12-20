package nl.erends.advent;


import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.*;

//bot 85 gives low to bot 93 and high to bot 191
public class Day10 extends AbstractProblem<List<String>, Integer> {
    
    private final Map<Integer, Bot> botMap = new HashMap<>();
    private final Map<Integer, Integer> outputMap = new HashMap<>();
    private int answer1;
    private int highCheck = 61;
    private int lowCheck = 17;

    public static void main(String[] args) {
        new Day10().setAndSolve(Util.readInput(2016, 10));
    }
    
    @Override
    public Integer solve1() {
        List<String> lines = input;
        generateBots(lines);
        while (!lines.isEmpty()) {
            lines.removeIf(this::processTransaction);
        }
        answer2 = outputMap.get(0) * outputMap.get(1) * outputMap.get(2);
        return answer1;
    }
    
    private void generateBots(List<String> lines) {
        Iterator<String> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            String[] words = line.split(" ");
            if ("value".equals(words[0])) {
                Bot bot = botMap.computeIfAbsent(Integer.valueOf(words[5]), Bot::new);
                bot.giveValue(Integer.parseInt(words[1]));
                lineIterator.remove();
            }
        }
    }
    
    private boolean processTransaction(String line) {
        String[] words = line.split(" ");
        Bot fromBot = botMap.computeIfAbsent(Integer.valueOf(words[1]), Bot::new);
        if (fromBot.isReady()) {
            if (fromBot.lowValue == lowCheck && fromBot.highValue == highCheck) {
                answer1 = fromBot.id;
            }
            if ("bot".equals(words[5])) {
                Bot toBot = botMap.computeIfAbsent(Integer.valueOf(words[6]), Bot::new);
                toBot.giveValue(fromBot.lowValue);
            } else {
                outputMap.put(Integer.valueOf(words[6]), fromBot.lowValue);
            }
            if ("bot".equals(words[10])) {
                Bot toBot = botMap.computeIfAbsent(Integer.valueOf(words[11]), Bot::new);
                toBot.giveValue(fromBot.highValue);
            } else {
                outputMap.put(Integer.valueOf(words[11]), fromBot.highValue);
            }
            return true;
        }
        return false;
    }

    private static class Bot {
        final int id;
        int lowValue;
        int highValue;

        Bot(int id) {
            this.id = id;
        }

        void giveValue(int newValue) {
            if (newValue < highValue) {
                lowValue = newValue;
            } else {
                lowValue = highValue;
                highValue = newValue;
            }
        }

        boolean isReady() {
            return (lowValue != 0 && highValue != 0);
        }
    }

    public void setHighCheck(int highCheck) {
        this.highCheck = highCheck;
    }

    public void setLowCheck(int lowCheck) {
        this.lowCheck = lowCheck;
    }
}
