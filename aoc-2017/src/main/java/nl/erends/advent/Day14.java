package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

public class Day14 extends AbstractProblem<String, Integer> {

    private final Square[][] squares = new Square[128][128];

    void main() {
        new Day14().setAndSolve("hwlqcszp");
    }

    @Override
    public Integer solve1() {
        int usedSpaces = 0;
        for (int nonce = 0; nonce < 128; nonce++) {
            String knotHash = KnotHasher.hash(input + "-" + nonce);
            String binaryRep = toBinaryRep(knotHash);
            int x = 0;
            for (char c : binaryRep.toCharArray()) {
                Square square = new Square(x, nonce);
                if (c == '1') {
                    usedSpaces++;
                    square.data = true;
                }
                squares[nonce][x] = square;
                x++;
            }
        }
        int answer1 = usedSpaces;
        int group = 0;
        for (Square[] row : squares) {
            for (Square square : row) {
                if (square.data && square.isNotGrouped()) {
                    square.setGroup(++group);
                }
            }
        }
        answer2 = group;
        return answer1;
    }


    private String toBinaryRep(String input) {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < input.length(); index++) {
            String letter = input.substring(index, index + 1);
            int numberRep = Integer.parseInt(letter, 16);
            StringBuilder letterBuilder = new StringBuilder(Integer.toBinaryString(numberRep));
            while (letterBuilder.length() < 4) {
                letterBuilder.insert(0, '0');
            }
            output.append(letterBuilder);
        }
        return output.toString();
    }

    private class Square {
        private final int x;
        private final int y;
        private boolean data;
        private int group;

        Square(int x, int y) {
            this.x = x;
            this.y = y;
            this.data = false;
            this.group = 0;
        }

        void setGroup(int group) {
            this.group = group;
            if (x != 0) {
                Square square = squares[y][x - 1];
                square.setGroupWhenFree(group);
            }
            if (x != 127) {
                Square square = squares[y][x + 1];
                square.setGroupWhenFree(group);
            }
            if (y != 0) {
                Square square = squares[y - 1][x];
                square.setGroupWhenFree(group);
            }
            if (y != 127) {
                Square square = squares[y + 1][x];
                square.setGroupWhenFree(group);
            }
        }

        boolean isNotGrouped() {
            return group == 0;
        }
        
        void setGroupWhenFree(int group) {
            if (data && isNotGrouped()) {
                setGroup(group);
            }
        }
    }
}
