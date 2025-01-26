package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 extends AbstractProblem<List<String>, String> {

    public static void main(String[] args) {
        new Day21().setAndSolve(Util.readInput(2020, 21));
    }

    @Override
    public String solve1() {
        List<Food> foods = input.stream().map(Food::new).toList();
        Map<String, String> badIngredients = new HashMap<>();
        Set<String> allergies = foods.stream().map(f -> f.allergies).flatMap(Collection::stream).collect(Collectors.toSet());
        while (!allergies.isEmpty()) {
            Iterator<String> it = allergies.iterator();
            while (it.hasNext()) {
                String currentAllergy = it.next();
                List<Food> possibleFood = foods.stream().filter(f -> f.allergies.contains(currentAllergy)).toList();
                List<String> possibleIngredient = new ArrayList<>(possibleFood.getFirst().ingredient);
                possibleFood.forEach(pf -> possibleIngredient.removeIf(pi -> !pf.ingredient.contains(pi)));
                possibleIngredient.removeIf(badIngredients::containsKey);
                if (possibleIngredient.size() == 1) {
                    it.remove();
                    badIngredients.put(possibleIngredient.getFirst(), currentAllergy);
                }
            }
        }

        String answer1 = Long.toString(foods.stream()
                .map(f -> f.ingredient)
                .flatMap(Collection::stream)
                .filter(i -> !badIngredients.containsKey(i))
                .count());

        answer2 = badIngredients.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(","));

        return answer1;
    }

    private static class Food {
        final List<String> ingredient = new ArrayList<>();
        final List<String> allergies = new ArrayList<>();

        private Food(String line) {
            String[] words = line.substring(0, line.length() - 1).split(" \\(contains ");
            ingredient.addAll(Arrays.asList(words[0].split(" ")));
            allergies.addAll(Arrays.asList(words[1].split(", ")));
        }
    }
}
