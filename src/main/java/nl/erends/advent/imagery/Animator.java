package nl.erends.advent.imagery;

import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Usage: Call setFilename and setColorMap first. Next, call addGrid() for every
 * frame needed. Finally, makeGif() will write a 60 second gif to disk.
 */
public class Animator {

    private static final List<Color[][]> colorList = new ArrayList<>();
    private static Map<Object, Color> colorMap;
    private static String filename;
    private static int xFrame;
    private static int yFrame;
    private static String frameCount;
    private static int scale;
    private static BufferedImage image;
    private static Graphics2D graphics;
    private static final int MARGIN = 10;
    private static final int TOP_MARGIN = 30;

    private static final Logger LOG = LogManager.getLogger(Animator.class);

    private Animator() {
    }
    
    public static void addGrid(char[][] grid) {
        Color[][] colorGrid = new Color[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                colorGrid[y][x] = colorMap.get(grid[y][x]);
            }
        }
        colorList.add(colorGrid);
    }

    public static void clear() {
        colorList.clear();
    }

    public static void makeGif() {
        if (colorList.isEmpty()) {
            return;
        }
        LOG.info("Start making gif");
        frameCount = Integer.toString(colorList.size());
        xFrame = colorList.get(0)[0].length;
        yFrame = colorList.get(0).length;
        scale = 0;
        do {
            scale++;
        } while (xFrame * scale <= 1000 && yFrame * scale <= 1000 && scale <= 5);
        int xImage = xFrame * scale + 2 * MARGIN;
        int yImage = yFrame * scale + MARGIN + TOP_MARGIN;
        image = new BufferedImage(xImage, yImage, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setFont(new Font("Courier New", Font.PLAIN, 16));
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, xImage, yImage);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(MARGIN - 1, TOP_MARGIN - 1, xFrame * scale + 1, yFrame * scale + 1);
        int count = 0;
        int frameRate = colorList.size() / 57;
        try {
            GifSequenceWriter.startGif(filename, frameRate);
            for (Color[][] frame : colorList) {
                addFrame(frame, count++);
                Timer.tick(count + "/" + colorList.size());
            }
            for (int i = 0; i < frameRate * 3; i++) {
                addFrame(colorList.get(colorList.size() - 1), count);
            }
            GifSequenceWriter.closeGif();
        } catch (IOException e) {
            LOG.error(e);
        }
        LOG.info("Written gif to {}", filename);
    }

    private static void addFrame(Color[][] frame, int number) throws IOException {
        String count = Util.leftPadWithZero(Integer.toString(number), frameCount.length());
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, xFrame, TOP_MARGIN - 1);
        graphics.setColor(Color.BLACK);
        graphics.drawString(count + '/' + frameCount, MARGIN, TOP_MARGIN - 7);
        for (int x = 0; x < xFrame; x++) {
            for (int y = 0; y < yFrame; y++) {
                graphics.setColor(frame[y][x]);
                graphics.fillRect(MARGIN + x * scale, TOP_MARGIN + y * scale, scale, scale);
            }
        }
        GifSequenceWriter.addImage(image);
    }
    
    public static void setFilename(int year, int day) {
        filename = String.format("%dday%02d.gif", year, day);
    }

    public static void setColorMap(Map<Object, Color> colorMap) {
        Animator.colorMap = colorMap;
    }
}
