package nl.erends.advent.year2016;


import nl.erends.advent.util.Util;

import java.util.*;

//bot 85 gives low to bot 93 and high to bot 191
public class Day10 {

    public static void main(String[] args) {
        List<String> lines = Util.getFileAsList("2016day10.txt");
        Map<Integer, Bot> bots = new HashMap<>();
        Map<Integer, List<Integer>> outputs = new HashMap<>();
        while (!lines.isEmpty()) {
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                String[] words = line.split(" ");
                if (words[0] .equals("value")) {
                    int value = Integer.parseInt(words[1]);
                    int id = Integer.parseInt(words[5]);
                    Bot toBot = bots.get(id);
                    if (toBot == null) {
                        toBot = new Bot(id);
                        bots.put(id, toBot);
                    }
                    toBot.giveValue(value);
                    bots.put(id, toBot);
                    iterator.remove();
                } else {
                    int fromBotID = Integer.parseInt(words[1]);
                    int lowBotID = Integer.parseInt(words[6]);
                    int highBotID = Integer.parseInt(words[11]);
                    Bot fromBot = bots.get(fromBotID);
                    if (fromBot != null && fromBot.isReady()) {
                        if (words[5].equals("bot")) {
                            Bot lowBot = bots.get(lowBotID);
                            if (lowBot == null) {
                                lowBot = new Bot(lowBotID);
                                bots.put(lowBotID, lowBot);
                            }
                            lowBot.giveValue(fromBot.getLowValue());
                        } else {
                            List<Integer> output = outputs.get(lowBotID);
                            if (output == null) {
                                output = new ArrayList<>();
                                outputs.put(lowBotID, output);
                            }
                            output.add(fromBot.getLowValue());
                        }
                        if (words[10].equals("bot")) {
                            Bot highBot = bots.get(highBotID);
                            if (highBot == null) {
                                highBot = new Bot(highBotID);
                                bots.put(highBotID, highBot);
                            }
                            highBot.giveValue(fromBot.getHighValue());
                        } else {
                            List<Integer> output = outputs.get(highBotID);
                            if (output == null) {
                                output = new ArrayList<>();
                                outputs.put(highBotID, output);
                            }
                            output.add(fromBot.getHighValue());
                        }
                        iterator.remove();
                        if (fromBot.getLowValue() == 17 && fromBot.getHighValue() == 61) {
                            System.out.println(fromBot.id);
                        }
                    }
                }
            }
        }
        System.out.println(outputs.get(0).get(0) * outputs.get(1).get(0) * outputs.get(2).get(0));
    }
}

class Bot {
    int id;
    int lowValue;
    int highValue;

    public Bot(int id) {
        this.id = id;
    }

    public void giveValue(int newValue) {
        if (newValue < highValue) {
            lowValue = newValue;
        } else {
            lowValue = highValue;
            highValue = newValue;
        }
    }

    public boolean isReady() {
        return (lowValue != 0 && highValue != 0);
    }

    public int getLowValue() {
        return lowValue;
    }

    public int getHighValue() {
        return highValue;
    }
}