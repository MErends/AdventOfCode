package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 {


    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day13.txt");
        long start = System.currentTimeMillis();
        long mid = start;
        Track[][] map = new Track[input.size()][input.get(0).length()];
        List<Cart> carts = new ArrayList<>();
        for (int x = 0; x < input.get(0).length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                char c = input.get(y).charAt(x);
                switch (c) {
                    case ' ':
                        continue;
                    case '>': {
                        Cart cart = new Cart(x, y, Direction.RIGHT);
                        carts.add(cart);
                        Track track = new Track(x, y, cart, '-');
                        map[y][x] = track;
                        break;
                    } case 'v': {
                        Cart cart = new Cart(x, y, Direction.DOWN);
                        carts.add(cart);
                        Track track = new Track(x, y, cart, '|');
                        map[y][x] = track;
                        break;
                    } case '<': {
                        Cart cart = new Cart(x, y, Direction.LEFT);
                        carts.add(cart);
                        Track track = new Track(x, y, cart, '-');
                        map[y][x] = track;
                        break;
                    } case '^': {
                        Cart cart = new Cart(x, y, Direction.UP);
                        carts.add(cart);
                        Track track = new Track(x, y, cart, '|');
                        map[y][x] = track;
                        break;
                    } default: {
                        Track track = new Track(x, y, null, c);
                        map[y][x] = track;
                    }
                }
            }
        }
        boolean firstCollision = true;
        while (carts.size() != 1) {
            Collections.sort(carts);
            for (Cart cart : carts) {
                if (!cart.alive) continue;
                Track current = map[cart.y][cart.x];
                Track target = map[cart.y][cart.x];
                switch (cart.direction) {
                    case LEFT:
                        target = map[cart.y][cart.x - 1];
                        cart.x--;
                        break;
                    case UP:
                        target = map[cart.y - 1][cart.x];
                        cart.y--;
                        break;
                    case RIGHT:
                        target = map[cart.y][cart.x + 1];
                        cart.x++;
                        break;
                    case DOWN:
                        target = map[cart.y + 1][cart.x];
                        cart.y++;
                        break;
                }
                if (target.cart != null) {
                    if (firstCollision) {
                        mid = System.currentTimeMillis();
                        System.out.println(target.x + "," + target.y);
                        firstCollision = false;
                    }
                    current.cart.alive = false;
                    current.cart = null;
                    target.cart.alive = false;
                    target.cart = null;
                    continue;
                }
                target.cart = cart;
                current.cart = null;
                switch (target.type) {
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
                }
            }
            carts = carts.stream().filter(c -> c.alive).collect(Collectors.toList());
        }
        System.out.println(carts.get(0).x + "," + carts.get(0).y);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
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

        Cart(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        
        public String toString() {
            switch (direction) {
                case LEFT:
                    return "<";
                case UP:
                    return "^";
                case RIGHT:
                    return ">";
                default:
                    return "v";
            }
        }
    }

    private static class Track {
        private int x;
        private int y;
        private Cart cart;
        private char type;

        Track(int x, int y, Cart cart, char type) {
            this.x = x;
            this.y = y;
            this.cart = cart;
            this.type = type;
        }
        
        @Override
        public String toString() {
            if (cart == null) {
                return "" + type;
            } else {
                return cart.toString();
            }
        }
    }

    private enum Direction {
        LEFT {
            @Override
            public Direction turnLeft() {
                return DOWN;
            }
        },
        UP,
        RIGHT,
        DOWN {
            @Override
            public Direction turnRight() {
                return LEFT;
            }
        };
        
        public Direction turnLeft() {
            return values()[ordinal() - 1];
        }


        public Direction turnRight() {
            return values()[ordinal() + 1];
        }
    }
}
