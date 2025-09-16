package nl.erends.advent.util;

import java.util.HashMap;
import java.util.Map;

public record Coord(int x, int y, int z) {

    private static final Map<Integer, Map<Integer, Map<Integer, Coord>>> coordMap = new HashMap<>();
    public static final Coord ZERO = of(0, 0);

    public static Coord of(String line) {
        String[] split = line.split(",");
        int sx = Integer.parseInt(split[0]);
        int sy = Integer.parseInt(split[1]);
        int sz = Integer.parseInt(split[2]);
        return of(sx, sy, sz);
    }

    public static Coord of(int x, int y) {
        return of(x, y, 0);
    }

    public static synchronized Coord of(int x, int y, int z) {
        return coordMap.computeIfAbsent(x, _ -> new HashMap<>())
                .computeIfAbsent(y, _ -> new HashMap<>())
                .computeIfAbsent(z, _ -> new Coord(x, y, z));
    }

    public Coord addDirection(Direction d) {
        return of(x + d.dx(), y + d.dy());
    }

    public Coord addDirection(int dx, int dy) {
        return of(x + dx, y + dy);
    }

    public Coord addDirection(int dx, int dy, int dz) {
        return of(x + dx, y + dy, z + dz);
    }

    public int distanceTo(Coord other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
