package nl.erends.advent;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 13: Distress Signal ---
 * <p>You climb the hill and again try contacting the Elves. However, you
 * instead receive a signal you weren't expecting: a distress signal. Your
 * handheld device must still not be working properly; the packets from the
 * distress signal got decoded out of order. How many pairs of packets are in
 * the right order and what is the decoder key for the distress signal?
 * <p><a href="https://adventofcode.com/2022/day/13">2022 Day 13</a>
 */
public class Day13 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day13().setAndSolve(Util.readInput(2022, 13));
    }

    @Override
    protected Integer solve1() {
        int indexSum = 0;
        Gson gson = new Gson();
        for (int index = 0; index < input.size(); index += 3) {
            int number = (index / 3) + 1;
            JsonElement first = gson.fromJson(input.get(index), JsonElement.class);
            JsonElement second = gson.fromJson(input.get(index + 1), JsonElement.class);
            if (compare(first, second) < 0) {
                indexSum += number;
            }
        }
        return indexSum;
    }

    @Override
    public Integer solve2() {
        Gson gson = new Gson();
        JsonElement firstDivider = gson.fromJson("[[2]]", JsonElement.class);
        JsonElement secondDivider = gson.fromJson("[[6]]", JsonElement.class);
        List<JsonElement> packets = new ArrayList<>();
        packets.add(firstDivider);
        packets.add(secondDivider);
        input.stream()
                .filter(l -> !l.isBlank())
                .map(l -> gson.fromJson(l, JsonElement.class))
                .forEach(packets::add);
        packets.sort(this::compare);
        return (packets.indexOf(firstDivider) + 1) * (packets.indexOf(secondDivider) + 1);
    }

    private int compare(JsonElement first, JsonElement second) {
        if (first.isJsonArray()) {
            if (second.isJsonArray()) {
                return compare(first.getAsJsonArray(), second.getAsJsonArray());
            } else {
                return compare(first.getAsJsonArray(), second.getAsJsonPrimitive());
            }
        } else {
            if (second.isJsonArray()) {
                return compare(first.getAsJsonPrimitive(), second.getAsJsonArray());
            } else {
                return compare(first.getAsJsonPrimitive(), second.getAsJsonPrimitive());
            }
        }
    }

    private int compare(JsonArray first, JsonArray second) {
        int index = 0;
        while (index < first.size() && index < second.size()) {
            int compare = compare(first.get(index), second.get(index));
            if (compare != 0) {
                return compare;
            }
            index++;
        }
        return first.size() - second.size();
    }

    private int compare(JsonArray first, JsonPrimitive second) {
        JsonArray secondArray = new JsonArray();
        secondArray.add(second);
        return compare(first, secondArray);
    }

    private int compare(JsonPrimitive first, JsonArray second) {
        JsonArray firstArray = new JsonArray();
        firstArray.add(first);
        return compare(firstArray, second);
    }

    private int compare(JsonPrimitive first, JsonPrimitive second) {
        return Integer.compare(first.getAsInt(), second.getAsInt());
    }
}
