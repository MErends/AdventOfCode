package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;

import java.util.List;

public class Day22 {
    //          1111111111222222222233333333344444444
    //01234567890123456789012345678901234567890123456
    ///dev/grid/node-x7-y4     89T   64T    25T   71%
    ///dev/grid/node-x21-y24   92T   66T    26T   71%

    
    static Gridnode[][] grid;
    
    public static void main(String[] args) {
        List<String> gridnodes = Util.getFileAsList("year2016/2016day22.txt");
        gridnodes.remove(0);
        gridnodes.remove(0);
        Gridnode maxGridnode = new Gridnode(gridnodes.get(gridnodes.size() - 1));
        grid = new Gridnode[maxGridnode.getY() + 1][maxGridnode.getX() + 1];
        for (String input : gridnodes) {
            Gridnode gridnode = new Gridnode(input);
            grid[gridnode.getY()][gridnode.getX()] = gridnode;
        }
        int validPairs = 0;
        for (Gridnode[] rowA : grid) {
            for (Gridnode nodaA : rowA) {
                for (Gridnode[] rowB : grid) {
                    for (Gridnode nodeB : rowB) {
                        if (nodaA != nodeB && nodaA.getUsed() != 0 && nodaA.getUsed() <= nodeB.getAvailable()) {
                            validPairs++;
                        }
                    }
                }
            }
        }
        System.out.println(validPairs);
    }
    
    
}

class Gridnode {
    private String id;
    private int x;
    private int y;
    private int size;
    private int used;
    private int available;

    Gridnode(String input) {
        id = input.substring(0, 22).trim();
        x = Integer.parseInt(id.split("-")[1].substring(1));
        y = Integer.parseInt(id.split("-")[2].substring(1));
        size = Integer.parseInt(input.substring(22, 27).trim());
        used = Integer.parseInt(input.substring(28, 33).trim());
        available = Integer.parseInt(input.substring(34, 40).trim());
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}