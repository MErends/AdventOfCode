package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 21: Keypad Conundrum ---</h1>
 * <p>The door to the area is locked, but the computer can't open it; it can
 * only be opened by physically typing the door codes on the numeric keypad on
 * the door. Unfortunately, the area outside the door is currently depressurized
 * and nobody can go near the door. A robot needs to be sent instead.
 * Unfortunately, the area containing this directional keypad remote control is
 * currently experiencing high levels of radiation and nobody can go near it. A
 * robot needs to be sent instead. Unfortunately, the area containing this
 * second directional keypad remote control is currently -40 degrees! Another
 * robot will need to be sent to type on that directional keypad, too. Find the
 * fewest number of button presses you'll need to perform in order to cause the
 * robot in front of the door to type each code.</p>
 * <p><a href="https://adventofcode.com/2024/day/21">2024 Day 21</a></p>
 */
public class Day21 extends AbstractProblem<List<String>, Long> {

    final char[][] numpadSrc = new char[][]{  {'7', '8', '9'},
                                        {'4', '5', '6'},
                                        {'1', '2', '3'},
                                        {'\0', '0', 'A'}};

    final char[][] dpadSrc = new char[][]{    {'\0', '^', 'A'},
                                        {'<', 'v', '>'}};

    Map<Character, Coord> numpad;
    Map<Character, Coord> dpad;
    Map<Character, Map<Character, String>> pathMap;
    Map<Character, Map<Character, Map<Integer, Long>>> fromToDepthLengthMap;

    int robotCount = 2;

    static void main() {
        new Day21().setAndSolve(Util.readInput(2024, 21));
    }

    @Override
    protected Long solve1() {
        robotCount = part2 ? 25 : 2;
        numpad = new HashMap<>();
        for (int y = 0; y < numpadSrc.length; y++) {
            for (int x = 0; x < numpadSrc[y].length; x++) {
                numpad.put(numpadSrc[y][x], Coord.of(x, y));
            }
        }
        dpad = new HashMap<>();
        for (int y = 0; y < dpadSrc.length; y++) {
            for (int x = 0; x < dpadSrc[y].length; x++) {
                dpad.put(dpadSrc[y][x], Coord.of(x, y));
            }
        }
        pathMap = new HashMap<>();
        fromToDepthLengthMap = new HashMap<>();
        fillPathMap();
        long totalLength = 0;
        for (String line : input) {
            String path = getDPadString(line);
            long length = 0;
            char fromChar = 'A';
            for (char toChar : path.toCharArray()) {
                length += getPathLength(fromChar, toChar, 1);
                fromChar = toChar;
            }
            totalLength += (length * Integer.parseInt(line.substring(0, 3)));
        }
        return totalLength;
    }

    private String getDPadString(String numpadString) {
        Coord fromCoord = numpad.get('A');
        Coord emptyCoord = numpad.get('\0');
        StringBuilder path = new StringBuilder();
        for (char c : numpadString.toCharArray()) {
            Coord toCoord = numpad.get(c);
            path.append(getPath(fromCoord, toCoord, emptyCoord));
            fromCoord = toCoord;
        }
        return path.toString();
    }

    long getPathLength(char from, char to, int depth) {
        Map<Character, Map<Integer, Long>> fromMap = fromToDepthLengthMap.computeIfAbsent(from, _ -> new HashMap<>());
        Map<Integer, Long> toMap = fromMap.computeIfAbsent(to, _ -> new HashMap<>());
        if (toMap.containsKey(depth)) {
            return toMap.get(depth);
        }
        String path = pathMap.get(from).get(to);
        if (depth == robotCount) {
            return path.length();
        }
        long length = 0L;
        path = 'A' + path;
        for (int index = 0; index < path.length() - 1; index++) {
            length += getPathLength(path.charAt(index), path.charAt(index + 1), depth + 1);
        }
        toMap.put(depth, length);
        return length;
    }

    void fillPathMap() {
        List<Character> buttons = List.of('A', '<', '^', '>', 'v');
        for (char from : buttons) {
            for (char to : buttons) {
                Coord fromCoord = dpad.get(from);
                Coord toCoord = dpad.get(to);
                Coord empty = dpad.get('\0');
                String path = getPath(fromCoord, toCoord, empty);
                pathMap.computeIfAbsent(from, _ -> new HashMap<>()).put(to, path);
            }
        }
    }

    private String getPath(Coord fromCoord, Coord toCoord, Coord empty) {
        String path = "";
        if (goHorizontalFirst(fromCoord, toCoord, empty)) {
            path += getHorizontalPath(fromCoord, toCoord);
            path += getVerticalPath(fromCoord, toCoord);
        } else {
            path += getVerticalPath(fromCoord, toCoord);
            path += getHorizontalPath(fromCoord, toCoord);
        }
        path += 'A';
        return path;
    }

    boolean goHorizontalFirst(Coord fromCoord, Coord toCoord, Coord empty) {
        if (fromCoord.y() == empty.y() && toCoord.x() == empty.x()) {
            return false;
        }
        return fromCoord.x() == empty.x() && toCoord.y() == empty.y() || (toCoord.x() < fromCoord.x());
    }

    String getVerticalPath(Coord from, Coord to) {
        int dy = to.y() - from.y();
        char step = 'v';
        if (dy < 0) {
            dy *= -1;
            step = '^';
        }
        return String.valueOf(step).repeat(dy);
    }

    String getHorizontalPath(Coord from, Coord to) {
        int dx = to.x() - from.x();
        char step = '>';
        if (dx < 0) {
            dx *= -1;
            step = '<';
        }
        return String.valueOf(step).repeat(dx);
    }
}
