package nl.erends.advent.year2015;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        int maxScore = 0;
        Ingredient frosting = new Ingredient(4, -2, 0, 0, 5);
        Ingredient candy = new Ingredient(0, 5, -1, 0, 8);
        Ingredient butterscotch = new Ingredient(-1, 0, 5, 0, 6);
        Ingredient sugar = new Ingredient(0, 0, -2, 2, 1);
        
        
        for (int tbsFrosting = 0; tbsFrosting <= 100; tbsFrosting++) {
            for (int tbsCandy = 0; tbsCandy <= 100 - tbsCandy; tbsCandy++) {
                for (int tbsButterscotch = 0; tbsButterscotch <= 100 - tbsFrosting - tbsCandy; tbsButterscotch++) {
                    int tbsSugar = 100 - tbsFrosting - tbsCandy - tbsButterscotch;
                    int totalCapacity = tbsFrosting * frosting.getCapacity();
                    totalCapacity += tbsCandy * candy.getCapacity();
                    totalCapacity += tbsButterscotch * butterscotch.getCapacity();
                    totalCapacity += tbsSugar * sugar.getCapacity();
                    totalCapacity = Math.max(0, totalCapacity);

                    int totalDurability = tbsFrosting * frosting.getDurability();
                    totalDurability += tbsCandy * candy.getDurability();
                    totalDurability += tbsButterscotch * butterscotch.getDurability();
                    totalDurability += tbsSugar * sugar.getDurability();
                    totalDurability = Math.max(0, totalDurability);

                    int totalFlavor = tbsFrosting * frosting.getFlavor();
                    totalFlavor += tbsCandy * candy.getFlavor();
                    totalFlavor += tbsButterscotch * butterscotch.getFlavor();
                    totalFlavor += tbsSugar * sugar.getFlavor();
                    totalFlavor = Math.max(0, totalFlavor);

                    int totalTexture = tbsFrosting * frosting.getTexture();
                    totalTexture += tbsCandy * candy.getTexture();
                    totalTexture += tbsButterscotch * butterscotch.getTexture();
                    totalTexture += tbsSugar * sugar.getTexture();
                    totalTexture = Math.max(0, totalTexture);

                    int totalCalories = tbsFrosting * frosting.getCalories();
                    totalCalories += tbsCandy * candy.getCalories();
                    totalCalories += tbsButterscotch * butterscotch.getCalories();
                    totalCalories += tbsSugar * sugar.getCalories();
                    totalCalories = Math.max(0, totalCalories);

                    int totalScore = totalCapacity * totalDurability * totalFlavor * totalTexture;
                    if (totalCalories == 500) {
                        maxScore = Math.max(maxScore, totalScore);
                    }
                }
            }
        }
        
        System.out.println(maxScore);
    }

}

class Ingredient {
    int capacity;
    int durability;
    int flavor;
    int texture;
    int calories;

    public Ingredient(int capacity, int durability, int flavor, int texture, int calories) {
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDurability() {
        return durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public int getTexture() {
        return texture;
    }

    public int getCalories() {
        return calories;
    }
}