package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class Day22 extends AbstractProblem<List<String>, Integer> {

    private int targetX;
    private int targetY;
    private int depth;
    
    private int[][] erosionGrid;
    private int[][] timeGrid;
    private Region[][] regionGrid;
    private int lowestGoal = Integer.MAX_VALUE;
    private final TreeSet<GridState> states = new TreeSet<>();
    
    void main() {
        new Day22().setAndSolve(Util.readInput(2018, 22));
    }
    
    @Override
    public Integer solve1() {
        depth = Integer.parseInt(input.get(0).split(" ")[1]);
        targetX = Integer.parseInt(input.get(1).split(" ")[1].split(",")[0]);
        targetY = Integer.parseInt(input.get(1).split(" ")[1].split(",")[1]);
        erosionGrid = new int[targetY + targetX][targetX + targetY];
        timeGrid = new int[targetY + targetX][targetX + targetY];
        regionGrid = new Region[targetY + targetX][targetX + targetY];
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
        return totalRisk;
    }
    
    @Override
    public Integer solve2() {
        for (int[] row : timeGrid) {
            Arrays.fill(row, Integer.MAX_VALUE - 20);
        }
        states.add(new GridState(0, 0, Gear.TORCH, 0));
        while(!states.isEmpty()) {
            GridState state = states.pollFirst();
            calculateTimeFrom(Objects.requireNonNull(state));
        }
        return lowestGoal;
    }
    
    
    private int erosionAt(int x, int y) {
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
        erosionInt = (geologic + depth) % 20183;
        erosionGrid[y][x] = erosionInt;
        return erosionInt;
    }
    
    private enum Region {
        ROCKY(0),
        WET(1),
        NARROW(2);
        
        private final int type;
        
        Region(int type) {
            this.type = type;
        }
        
        static Region valueOf(int value) {
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
    
    private void calculateTimeFrom(GridState state) {
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
        // GO down?
        if (y + 1 < regionGrid.length) {
            checkDown(x, y, presentGear, stateTime, presentRegion);
        }
        // GO right?
        if (x + 1 < regionGrid[y].length) {
            checkRight(x, y, presentGear, stateTime, presentRegion);
        }
        // GO up?
        if (y - 1 >= 0) {
            checkUp(x, y, presentGear, stateTime, presentRegion);
        }
        // GO left?
        if (x - 1 >= 0) {
            checkLeft(x, y, presentGear, stateTime, presentRegion);
        }
    }

    private void checkLeft(int x, int y, Gear presentGear, int stateTime, Region presentRegion) {
        Region targetRegion = regionGrid[y][x - 1];
        switch (targetRegion) {
            case ROCKY:
                if (presentGear != Gear.NEITHER) {
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

    private void checkUp(int x, int y, Gear presentGear, int stateTime, Region presentRegion) {
        Region targetRegion = regionGrid[y - 1][x];
        switch (targetRegion) {
            case ROCKY:
                if (presentGear != Gear.NEITHER) {
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

    private void checkRight(int x, int y, Gear presentGear, int stateTime, Region presentRegion) {
        Region targetRegion = regionGrid[y][x + 1];
        switch (targetRegion) {
            case ROCKY:
                if (presentGear != Gear.NEITHER) {
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

    private void checkDown(int x, int y, Gear presentGear, int stateTime, Region presentRegion) {
        Region targetRegion = regionGrid[y + 1][x];
        switch (targetRegion) {
            case ROCKY:
                if (presentGear != Gear.NEITHER) {
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

    private record GridState(int x, int y,
                             Gear presentGear,
                             int time) implements Comparable<GridState> {
        @Override
        public int compareTo(GridState other) {
            if (time != other.time) return Integer.compare(time, other.time);
            if (x != other.x) return Integer.compare(x, other.x);
            if (y != other.y) return Integer.compare(y, other.y);
            return presentGear == other.presentGear ? 0 : 1;
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
