package nl.erends.advent.year2018;

import java.util.*;

public class Day22 {

    private static int targetX = 9;
    private static int targetY = 751;
    
    private static int[][] erosionGrid = new int[targetY + targetX][targetX + targetY];
    private static int[][] timeGrid = new int[targetY + targetX][targetX + targetY];
    private static Region[][] regionGrid = new Region[targetY + targetX][targetX + targetY];
    private static int lowestGoal = Integer.MAX_VALUE;
    
    private static TreeSet<GridState> states = new TreeSet<>();
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int[] row : erosionGrid) {
            Arrays.fill(row, -1);
        }
        erosionAt(targetX + targetY - 1, targetY + targetX - 1);
        erosionGrid[0][0] = 0;

        int totalRisk = 0;
        for (int x = 0; x < targetX + targetY; x++) {
            for (int y = 0; y < targetX + targetY; y++) {
                int erosionInt = erosionGrid[y][x];
                int risk = erosionInt % 3;
                regionGrid[y][x] = Region.valueOf(risk);
                if (y <= targetY && x <= targetX) {
                    totalRisk += risk;
                }
            }
        }
        System.out.println(totalRisk);
        long mid = System.currentTimeMillis();
        for (int[] row : timeGrid) {
            Arrays.fill(row, Integer.MAX_VALUE - 20);
        }
        states.add(new GridState(0, 0, Gear.TORCH, 0));
        while(!states.isEmpty()) {
            GridState state = states.pollFirst();
            calculateTimeFrom(Objects.requireNonNull(state));
        }
        System.out.println(lowestGoal);
        long end = System.currentTimeMillis();
//        
//        for (Region[] row : regionGrid) {
//            for (Region r : row) {
//                System.out.print(r == Region.ROCKY ? '.' : r == Region.WET ? '=' : '|');
//            }
//            System.out.println();
//        }
//        
//        for (int[] row : timeGrid) {
//            for (int time : row) {
//                System.out.printf("%3d", time);
//            }
//            System.out.println();
//        }
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    
    private static int erosionAt(int x, int y) {
        int erosionInt = erosionGrid[y][x];
        if (erosionInt != -1) return erosionInt;
        int geologic;
        if (x == 0) {
            geologic = 48271 * y;
        } else if (y == 0) {
            geologic = 16807 * x;
        } else if (targetX == x && targetY == y) {
            geologic = 0;
        } else {
            geologic = erosionAt(x - 1, y) * erosionAt(x, y - 1);
        }
        int depth = 11817;
        erosionInt = (geologic + depth) % 20183;
        erosionGrid[y][x] = erosionInt;
        return erosionInt;
    }
    
    private enum Region {
        ROCKY(0),
        WET(1),
        NARROW(2);
        
        private int type;
        
        Region(int type) {
            this.type = type;
        }
        
        public static Region valueOf(int value) {
            for (Region region : values()) {
                if (region.type == value) {
                    return region;
                }
            }
            throw new IllegalStateException();
        }
    }
    
    private enum Gear {
        TORCH,
        CLIMBING,
        NEITHER
    }
    
    private static void calculateTimeFrom(GridState state) {
        int x = state.x;
        int y = state.y;
        Gear presentGear = state.presentGear;
        int stateTime = state.time;
        int gridTime = timeGrid[y][x];
        if (stateTime < gridTime) {
            timeGrid[y][x] = stateTime;
        }
        if (stateTime > gridTime + 8) return;
        if (x == targetX && y == targetY) {
            if (presentGear != Gear.TORCH) {
                lowestGoal = Math.min(lowestGoal, stateTime + 7);
            } else {
                lowestGoal = Math.min(lowestGoal, stateTime);
            }
        }
        Region presentRegion = regionGrid[y][x];
        if ((presentRegion == Region.ROCKY && presentGear == Gear.NEITHER)
            || (presentRegion == Region.WET && presentGear == Gear.TORCH)
            || (presentRegion == Region.NARROW && presentGear == Gear.CLIMBING)) {
            throw new IllegalStateException();
        }
        // GO down?
        if (y + 1 < regionGrid.length) {
            Region targetRegion = regionGrid[y + 1][x];
            switch (targetRegion) {
                case ROCKY:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.TORCH) {
                        states.add(new GridState(x, y + 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x, y + 1, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x, y + 1, Gear.TORCH, stateTime + 8));
                    }
                    break;
                case WET:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x, y + 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x, y + 1, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x, y + 1, Gear.NEITHER, stateTime + 8));
                    }
                    break;
                case NARROW:
                    if (presentGear == Gear.TORCH || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x, y + 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x, y + 1, Gear.TORCH, stateTime + 8));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x, y + 1, Gear.NEITHER, stateTime + 8));
                    }
                    break;
            }
        }
        // GO right?
        if (x + 1 < regionGrid[y].length) {
            Region targetRegion = regionGrid[y][x + 1];
            switch (targetRegion) {
                case ROCKY:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.TORCH) {
                        states.add(new GridState(x + 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x + 1, y, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x + 1, y, Gear.TORCH, stateTime + 8));
                    }
                    break;
                case WET:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x + 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x + 1, y, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x + 1, y, Gear.NEITHER, stateTime + 8));
                    }
                    break;
                case NARROW:
                    if (presentGear == Gear.TORCH || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x + 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x + 1, y, Gear.TORCH, stateTime + 8));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x + 1, y, Gear.NEITHER, stateTime + 8));
                    }
                    break;
            }
        }
        // GO up?
        if (y - 1 >= 0) {
            Region targetRegion = regionGrid[y - 1][x];
            switch (targetRegion) {
                case ROCKY:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.TORCH) {
                        states.add(new GridState(x, y - 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x, y - 1, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x, y - 1, Gear.TORCH, stateTime + 8));
                    }
                    break;
                case WET:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x, y - 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x, y - 1, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x, y - 1, Gear.NEITHER, stateTime + 8));
                    }
                    break;
                case NARROW:
                    if (presentGear == Gear.TORCH || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x, y - 1, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x, y - 1, Gear.TORCH, stateTime + 8));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x, y - 1, Gear.NEITHER, stateTime + 8));
                    }
                    break;
            }
        }
        // GO left?
        if (x - 1 >= 0) {
            Region targetRegion = regionGrid[y][x - 1];
            switch (targetRegion) {
                case ROCKY:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.TORCH) {
                        states.add(new GridState(x - 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x - 1, y, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x - 1, y, Gear.TORCH, stateTime + 8));
                    }
                    break;
                case WET:
                    if (presentGear == Gear.CLIMBING || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x - 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x - 1, y, Gear.CLIMBING, stateTime + 8));
                    } else if (presentRegion == Region.NARROW) {
                        states.add(new GridState(x - 1, y, Gear.NEITHER, stateTime + 8));
                    }
                    break;
                case NARROW:
                    if (presentGear == Gear.TORCH || presentGear == Gear.NEITHER) {
                        states.add(new GridState(x - 1, y, presentGear, stateTime + 1));
                    } else if (presentRegion == Region.ROCKY) {
                        states.add(new GridState(x - 1, y, Gear.TORCH, stateTime + 8));
                    } else if (presentRegion == Region.WET) {
                        states.add(new GridState(x - 1, y, Gear.NEITHER, stateTime + 8));
                    }
                    break;
            }
        }
    }
    
    
    private static class GridState implements Comparable<GridState> {
        int x;
        int y;
        Gear presentGear;
        int time;
        
        @Override
        public int compareTo(GridState other) {
            if (time != other.time) return Integer.compare(time, other.time);
            if (x != other.x) return Integer.compare(x, other.x);
            if (y != other.y) return Integer.compare(y, other.y);
            return presentGear == other.presentGear ? 0 : 1;
        }

        GridState(int x, int y, Gear presentGear, int time) {
            this.x = x;
            this.y = y;
            this.presentGear = presentGear;
            this.time = time;
        }

        @Override
        public String toString() {
            return "GridState{" +
                    "x=" + x +
                    ", y=" + y +
                    ", presentGear=" + presentGear +
                    ", time=" + time +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GridState gridState = (GridState) o;
            return x == gridState.x &&
                    y == gridState.y &&
                    time == gridState.time &&
                    presentGear == gridState.presentGear;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y, presentGear, time);
        }
    }
}
