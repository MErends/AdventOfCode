package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day15 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day15().setAndSolve(Util.readInput(2015, 15));
    }

    @Override
    public Integer solve1() {
        String[] words = input.get(0).split(" ");
        Ingredient frosting = new Ingredient(readValue(words[2]), readValue(words[4]), readValue(words[6]), readValue(words[8]), readValue(words[10]));
        words = input.get(1).split(" ");
        Ingredient candy = new Ingredient(readValue(words[2]), readValue(words[4]), readValue(words[6]), readValue(words[8]), readValue(words[10]));
        words = input.get(2).split(" ");
        Ingredient butterscotch = new Ingredient(readValue(words[2]), readValue(words[4]), readValue(words[6]), readValue(words[8]), readValue(words[10]));
        words = input.get(3).split(" ");
        Ingredient sugar = new Ingredient(readValue(words[2]), readValue(words[4]), readValue(words[6]), readValue(words[8]), readValue(words[10]));


        int maxScore = 0;
        int max500Score = 0;
        
        for (int tbsFrosting = 0; tbsFrosting <= 100; tbsFrosting++) {
            for (int tbsCandy = 0; tbsCandy <= 100 - tbsFrosting; tbsCandy++) {
                for (int tbsButterscotch = 0; tbsButterscotch <= 100 - tbsFrosting - tbsCandy; tbsButterscotch++) {
                    int tbsSugar = 100 - tbsFrosting - tbsCandy - tbsButterscotch;
                    int totalCapacity = tbsFrosting * frosting.capacity;
                    totalCapacity += tbsCandy * candy.capacity;
                    totalCapacity += tbsButterscotch * butterscotch.capacity;
                    totalCapacity += tbsSugar * sugar.capacity;
                    totalCapacity = Math.max(0, totalCapacity);

                    int totalDurability = tbsFrosting * frosting.durability;
                    totalDurability += tbsCandy * candy.durability;
                    totalDurability += tbsButterscotch * butterscotch.durability;
                    totalDurability += tbsSugar * sugar.durability;
                    totalDurability = Math.max(0, totalDurability);

                    int totalFlavor = tbsFrosting * frosting.flavor;
                    totalFlavor += tbsCandy * candy.flavor;
                    totalFlavor += tbsButterscotch * butterscotch.flavor;
                    totalFlavor += tbsSugar * sugar.flavor;
                    totalFlavor = Math.max(0, totalFlavor);

                    int totalTexture = tbsFrosting * frosting.texture;
                    totalTexture += tbsCandy * candy.texture;
                    totalTexture += tbsButterscotch * butterscotch.texture;
                    totalTexture += tbsSugar * sugar.texture;
                    totalTexture = Math.max(0, totalTexture);

                    int totalCalories = tbsFrosting * frosting.calories;
                    totalCalories += tbsCandy * candy.calories;
                    totalCalories += tbsButterscotch * butterscotch.calories;
                    totalCalories += tbsSugar * sugar.calories;
                    totalCalories = Math.max(0, totalCalories);

                    int totalScore = totalCapacity * totalDurability * totalFlavor * totalTexture;
                    maxScore = Math.max(maxScore, totalScore);
                    if (totalCalories == 500) {
                        max500Score = Math.max(max500Score, totalScore);
                    }
                }
            }
        }
        answer2 = max500Score;
        return maxScore;
    }

    private int readValue(String input) {
        if (input.indexOf(',') != -1) {
            input = input.substring(0, input.length() - 1);
        }
        return Integer.parseInt(input);
    }

    private record Ingredient(int capacity, int durability, int flavor, int texture, int calories) {
    }
}
