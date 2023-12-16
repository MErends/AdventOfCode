package nl.erends.advent.util;

public enum Direction {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }

    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            default -> DOWN;
        };
    }
    
    public Direction turnLeft() {
        return switch (this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            default -> UP;
        };
    }

    public static Direction getDirection(char c) {
        return switch (c) {
            case 'U' -> Direction.UP;
            case 'D' -> Direction.DOWN;
            case 'R' -> Direction.RIGHT;
            case 'L' -> Direction.LEFT;
            default -> throw new IllegalArgumentException("" + c);
        };
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public boolean isHorizontal() {
        return !isVertical();
    }
}
