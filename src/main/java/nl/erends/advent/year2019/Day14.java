package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day14 extends AbstractProblem<List<String>, Integer> {

    private Map<String, List<String>> recipies;
    private Set<String> resourcesStillProduced;
    private Map<String, Integer> resourcesRequired;
    private Map<String, Long> inventory;

    public static void main(String[] args) {
        new Day14().setAndSolve(Util.readInput(2019, 14));
    }

    @Override
    public Integer solve1() {
        readRecipies();
        updateSet();
        resourcesRequired = new HashMap<>();
        resourcesRequired.put("FUEL", 1);
        while (resourcesRequired.size() != 1 || !resourcesRequired.containsKey("ORE")) {
            String resource = resourcesRequired.keySet().stream()
                    .filter(r -> !resourcesStillProduced.contains(r))
                    .findFirst().orElseThrow(IllegalStateException::new);
            resolveResource(resource, resourcesRequired.get(resource));
            updateSet();
        }
        return resourcesRequired.get("ORE");
    }
    
    private void updateSet() {
        resourcesStillProduced = new HashSet<>();
        for (List<String> recipeList : recipies.values()) {
            recipeList.forEach(r -> resourcesStillProduced.add(r.split(" ")[1]));
        }
    }
    
    private void resolveResource(String resource, int amountNeeded) {
        Map.Entry<String, List<String>> recipe = recipies.entrySet().stream()
                .filter(e -> e.getKey().contains(resource)).findFirst().orElseThrow(IllegalStateException::new);
        int yield = Integer.parseInt(recipe.getKey().split(" ")[0]);
        int multiplier = 1;
        while (multiplier * yield < amountNeeded) {
            multiplier++;
        }
        int finalMultiplier = multiplier;
        for (String required : recipe.getValue()) {
            String[] words = required.split(" ");
            resourcesRequired.compute(words[1], (k, v) -> finalMultiplier * Integer.parseInt(words[0]) + (v != null ? v : 0));
        }
        resourcesRequired.remove(resource);
        recipies.remove(recipe.getKey());
    }
    
    private void readRecipies() {
        recipies = new HashMap<>();
        for (String line : input) {
            String[] recipe = line.split(" => ");
            List<String> values = new ArrayList<>();
            for (String value : recipe[0].split(",")) {
                values.add(value.trim());
            }
            recipies.put(recipe[1], values);
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Integer solve2() {
        int oreOneFuel = solve1();
        readRecipies();
        inventory = new HashMap<>();
        inventory.put("ORE", 1_000_000_000_000L);
        while (oreOneFuel < inventory.get("ORE")) {
            create("FUEL", inventory.get("ORE") / oreOneFuel);
        }
        while (true) {
            boolean created = create("FUEL", 1);
            if (!created) {
                break;
            }
            inventory.entrySet().removeIf(e -> e.getValue() == 0);
        }
        return (int) inventory.get("FUEL").longValue();
    }
    
    private boolean create(String resource, long amountWanted) {
        if (resource.equals("ORE")) {
            return false;
        }
        Map.Entry<String, List<String>> recipe = recipies.entrySet().stream()
                .filter(e -> e.getKey().split(" ")[1].equals(resource)).findFirst().orElseThrow(IllegalStateException::new);
        long recipeAmount = Integer.parseInt(recipe.getKey().split(" ")[0]);
        long multiplier = 1;
        while (recipeAmount * multiplier < amountWanted) {
            multiplier++;
        }
        long finalRecipeAmount = recipeAmount * multiplier;
        for (String requirement : recipe.getValue()) {
            String ingredient = requirement.split(" ")[1];
            long ingredientNeeded = Integer.parseInt(requirement.split(" ")[0]) * multiplier;
            long ingredientAvailable = inventory.computeIfAbsent(ingredient, k -> 0L); 
            if (ingredientNeeded > ingredientAvailable) {
                boolean create = create(ingredient, ingredientNeeded - ingredientAvailable);
                if (!create) return false;
            }
            inventory.compute(ingredient, (k,v) -> (v != null ? v : 0) - ingredientNeeded);
        }
        inventory.compute(resource, (k, v) -> (v != null ? v : 0) + finalRecipeAmount);
        return true;
    }
}
