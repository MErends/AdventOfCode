package nl.erends.advent.year2016;

public class Day18 {

    public static void main(String[] args) {
        String firstRow = "^.^^^..^^...^.^..^^^^^.....^...^^^..^^^^.^^.^^^^^^^^.^^.^^^^...^^...^^^^.^.^..^^..^..^.^^.^.^.......";
        char[][] floor = new char[400000][firstRow.length()];
        for (int x = 0; x < firstRow.length(); x++) {
            floor[0][x] = firstRow.charAt(x);
        }
        for (int y = 1; y < floor.length; y++) {
            for (int x = 0; x < floor[y].length; x++) {
                floor[y][x] = determineChar(x, y, floor);
            }
        }
        int numSaveTiles = 0;
        for (int y = 0; y < floor.length; y++) {
            if (y == 40) {
                System.out.println(numSaveTiles);
            }
            for (int x = 0; x < floor[y].length; x++) {
                if (floor[y][x] == '.') numSaveTiles++;
            }
        }
        System.out.println(numSaveTiles); //2087 too high
    }


    private static char determineChar(int x, int y, char[][] floor) {
        char left = x == 0 ? '.' : floor[y - 1][x - 1];
        char center = floor[y - 1][x];
        char right = x == floor[0].length - 1 ? '.' : floor[y - 1][x + 1];
        String row = "" + left + center + right;
        return row.equals("^^.") || row.equals(".^^") || row.equals("^..") || row.equals("..^") ? '^' : '.';
    }
}
