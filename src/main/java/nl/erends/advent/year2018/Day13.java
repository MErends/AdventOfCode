package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day13 extends AbstractProblem<List<String>, String> {
    
    private List<Cart> carts;
    private Track[][] map;

    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readInput(2018, 13));
    }

    @Override
    public String solve1() {
        String answer1 = "";
        map = new Track[input.size()][input.get(0).length()];
        carts = new ArrayList<>();
        readCartsAndTrack();
        boolean firstCollision = true;
        while (carts.size() > 1) {
            Collections.sort(carts);
            for (Cart cart : carts) {
                if (!cart.alive) {
                    continue;
                }
                Track currentTrack = map[cart.y][cart.x];
                Track targetTrack = moveCart(cart);
                if (targetTrack.cart != null) {
                    if (firstCollision) {
                        answer1 = targetTrack.x + "," + targetTrack.y;
                        firstCollision = false;
                    }
                    currentTrack.cart.alive = false;
                    currentTrack.cart = null;
                    targetTrack.cart.alive = false;
                    targetTrack.cart = null;
                } else {
                    targetTrack.cart = cart;
                    currentTrack.cart = null;
                    rotateCart(cart, targetTrack);
                }
            }
            carts.removeIf(cart -> !cart.alive);
        }
        if (!carts.isEmpty()) {
            answer2 = carts.get(0).x + "," + carts.get(0).y;
        }
        return answer1;
    }

    private Track moveCart(Cart cart) {
        Track targetTrack = map[cart.y][cart.x];
        switch (cart.direction) {
            case LEFT -> {
                targetTrack = map[cart.y][cart.x - 1];
                cart.x--;
            }
            case UP -> {
                targetTrack = map[cart.y - 1][cart.x];
                cart.y--;
            }
            case RIGHT -> {
                targetTrack = map[cart.y][cart.x + 1];
                cart.x++;
            }
            case DOWN -> {
                targetTrack = map[cart.y + 1][cart.x];
                cart.y++;
            }
        }
        return targetTrack;
    }

    private void rotateCart(Cart cart, Track targetTrack) {
        switch (targetTrack.type) {
            case '/':
                cart.direction = cart.direction == Direction.UP || cart.direction == Direction.DOWN ? cart.direction.turnRight() : cart.direction.turnLeft();
                break;
            case '\\':
                cart.direction = cart.direction == Direction.UP || cart.direction == Direction.DOWN ? cart.direction.turnLeft() : cart.direction.turnRight();
                break;
            case '+':
                if (cart.nextTurn == Direction.LEFT) {
                    cart.direction = cart.direction.turnLeft();
                    cart.nextTurn = cart.nextTurn.turnRight();
                } else if (cart.nextTurn == Direction.UP) {
                    cart.nextTurn = cart.nextTurn.turnRight();
                } else {
                    cart.direction = cart.direction.turnRight();
                    cart.nextTurn = cart.nextTurn.turnRight().turnRight();
                }
                break;
            default:
        }
    }

    private void readCartsAndTrack() {
        for (int x = 0; x < input.get(0).length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                char c = input.get(y).charAt(x);
                Cart cart;
                Track track;
                switch (c) {
                    case ' ':
                        continue;
                    case '>':
                        cart = new Cart(x, y, Direction.RIGHT);
                        carts.add(cart);
                        track = new Track(x, y, cart, '-');
                        map[y][x] = track;
                        break;
                    case 'v':
                        cart = new Cart(x, y, Direction.DOWN);
                        carts.add(cart);
                        track = new Track(x, y, cart, '|');
                        map[y][x] = track;
                        break;
                    case '<':
                        cart = new Cart(x, y, Direction.LEFT);
                        carts.add(cart);
                        track = new Track(x, y, cart, '-');
                        map[y][x] = track;
                        break;
                    case '^':
                        cart = new Cart(x, y, Direction.UP);
                        carts.add(cart);
                        track = new Track(x, y, cart, '|');
                        map[y][x] = track;
                        break;
                    default:
                        track = new Track(x, y, null, c);
                        map[y][x] = track;
                }
            }
        }
    }

    private static class Cart implements Comparable<Cart> {
        private int x;
        private int y;
        private Direction direction;
        private Direction nextTurn = Direction.LEFT;
        private boolean alive = true;

        @Override
        public int compareTo(Cart o) {
            int xCompare = Integer.compare(x, o.x);
            return xCompare != 0 ? xCompare : Integer.compare(y, o.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cart cart = (Cart) o;
            return x == cart.x &&
                    y == cart.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        Cart(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    private static class Track {
        private final int x;
        private final int y;
        private Cart cart;
        private final char type;

        Track(int x, int y, Cart cart, char type) {
            this.x = x;
            this.y = y;
            this.cart = cart;
            this.type = type;
        }
    }
}
