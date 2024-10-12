package nl.erends.advent.util;

public class Coord {
    public int x;
    public int y;
    public int z;

    public Coord(Coord coord) {
        this.x = coord.x;
        this.y = coord.y;
        this.z = coord.z;
    }

    public Coord(String line) {
        String[] split = line.split(",");
        x = Integer.parseInt(split[0]);
        y = Integer.parseInt(split[1]);
        z = Integer.parseInt(split[2]);
    }

    public Coord(int x, int y) {
        this(x, y, 0);
    }

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public Coord addDirection(Direction d) {
        x += d.dx();
        y += d.dy();
        return this;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        if (x != coord.x) return false;
        if (y != coord.y) return false;
        return z == coord.z;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
