/**
 * 
 */
package com.glkwhr.snakegame;

import java.util.HashSet;
import java.util.Set;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class GameMap {
    
    // default size
    public static final int DEFAULT_ROW = 5;
    public static final int DEFAULT_COL = 5;
    
    // block type
    public static enum Block {
        EMPTY, BODY, APPLE, WALL, ERROR
    }
    
    private final Block[][] matrix;
    private final Apple apple;
    private final Set<Point> safeBlocks; // either EMPTY or APPLE
    // private int emptyBlockCounter;
    
    public GameMap() {
        this(DEFAULT_ROW, DEFAULT_COL);
    }
    
    public GameMap(int row, int col) {
        row = row > 1 ? row : 2;
        col = col > 1 ? col : 2;
        // generate game map with boundary
        matrix = new Block[row + 2][col + 2];
        safeBlocks = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[0].length - 1) {
                    matrix[i][j] = Block.WALL;
                } else {
                    matrix[i][j] = Block.EMPTY;
                    safeBlocks.add(new Point(i, j));
                    // emptyBlockCounter++;
                }
            }
        }
        apple = new Apple();
        Point applePos = apple.produce(this);
        matrix[applePos.x][applePos.y] = Block.APPLE;
    }
    
    public void eatApple() {
        if (apple.consume(this)) {
            setBlock(apple.produce(this), Block.APPLE);
        }
    }
    
    public Set<Point> getSafeBlocks() {
        return safeBlocks;
    }
    
    public Block getBlock(Point point) {
        Block blockType = Block.ERROR;
        if (point.x >= 0 && point.x < matrix.length 
                && point.y >=0 && point.y < matrix[0].length) {
            blockType = matrix[point.x][point.y];
        }
        return blockType;
    }
    
    public Block getBlock(int i, int j) {
        Block blockType = Block.ERROR;
        if (i >= 0 && i < matrix.length 
                && j >=0 && j < matrix[0].length) {
            blockType = matrix[i][j];
        }
        return blockType;
    }
    
    /*
     * set certain block to certain type
     * returns if the operation succeeded 
     * */
    public boolean setBlock(Point point, Block type) {
        boolean ret = false;
        if (point.x >= 0 && point.x < matrix.length 
                && point.y >=0 && point.y < matrix[0].length) {
            if ((matrix[point.x][point.y] == Block.EMPTY || matrix[point.x][point.y] == Block.APPLE)
                    && (type != Block.EMPTY && type != Block.APPLE)) {
                safeBlocks.remove(new Point(point));
                // emptyBlockCounter -= 1;
            } else if ((matrix[point.x][point.y] != Block.EMPTY && matrix[point.x][point.y] != Block.APPLE)
                    && (type == Block.EMPTY || type == Block.APPLE)) {
                safeBlocks.add(new Point(point));
                // emptyBlockCounter += 1;
            }
            matrix[point.x][point.y] = type;
            ret = true;
        }
        return ret;
    }
    
    public int getEmptyBlockCount() {
        return safeBlocks.size();
    }
    
    public int getRowCount() {
        return matrix.length;
    }
    
    public int getColCount() {
        return matrix[0].length;
    }
    
}
