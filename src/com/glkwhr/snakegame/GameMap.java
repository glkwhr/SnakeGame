package com.glkwhr.snakegame;

import java.util.HashSet;
import java.util.Set;

/**
 * GameMap class stores the information of the game map, including blocks and apples.
 * @author H.W.
 * @since   Aug 28, 2017   
 */
public class GameMap {
    
    /**
     * Types of the blocks on the game map.
     * @author H.W.
     * @since   Aug 28, 2017
     */
    public static enum Block {
        EMPTY, BODY, APPLE, WALL, ERROR
    }
    
    /**
     * Each element in this matrix represents a block on the map.
     */
    private final Block[][] matrix;
    
    /**
     * Blocks that are either {@link com.glkwhr.snakegame.GameMap.Block#EMPTY} or {@link com.glkwhr.snakegame.GameMap.Block#APPLE}. New apple would be placed randomly on one of those blocks.
     */
    private final Set<Point> safeBlocks;
    
    /**
     * The apple on the map.
     */
    private Apple apple;
    
    /**
     * Create a GameMap object with default row and column numbers.
     */
    public GameMap() {
        this(Settings.DEFAULT_ROW, Settings.DEFAULT_COL);
    }
    
    /**
     * Create a GameMap object using given row and column numbers.
     * @param row Number of the rows of the map (wall blocks not included).
     * @param col Number of the columns of the map (wall blocks not included).
     */
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
    }
    
    /**
     * Reset the map with the original settings.
     */
    public void reset() {
        safeBlocks.clear();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[0].length - 1) {
                    matrix[i][j] = Block.WALL;
                } else {
                    matrix[i][j] = Block.EMPTY;
                    safeBlocks.add(new Point(i, j));
                }
            }
        }
    }
    
    /**
     * Place an apple on the map.
     */
    public void produceApple() {
        apple = new Apple();
        Point applePos = apple.produce(this);
        if (applePos != null) {
            setBlock(applePos, Block.APPLE);
        }
    }
    
    /**
     * Eat the apple on the map. 
     */
    public void eatApple() {
        apple.consume(this);
    }
    
    /**
     * Get the set of safe blocks.
     * @return Set {@link com.glkwhr.snakegame.GameMap#safeBlocks}
     */
    public Set<Point> getSafeBlocks() {
        return safeBlocks;
    }
    
    /**
     * Get the location of the apple.
     * @return Point Location of the apple block in (row, col).
     */
    public Point getApple() {
        return apple.getPos();
    }
    
    /**
     * Get the block type of the map block on given location. 
     * @param point Location of the block.
     * @return Block Type of the block.
     */
    public Block getBlock(Point point) {
        Block blockType = Block.ERROR;
        if (point.x >= 0 && point.x < matrix.length 
                && point.y >=0 && point.y < matrix[0].length) {
            blockType = matrix[point.x][point.y];
        }
        return blockType;
    }
    
    /**
     * Get the block type of the map block on given location. 
     * @param i row
     * @param j col
     * @return Block Type of the block.
     */
    public Block getBlock(int i, int j) {
        Block blockType = Block.ERROR;
        if (i >= 0 && i < matrix.length 
                && j >=0 && j < matrix[0].length) {
            blockType = matrix[i][j];
        }
        return blockType;
    }
    
    /**
     * Set certain block to certain type
     * @param point Block location.
     * @param type New block type.
     * @return Boolean Whether the block is set. 
     */
    public boolean setBlock(Point point, Block type) {
        boolean ret = false;
        if (point.x >= 0 && point.x < matrix.length 
                && point.y >=0 && point.y < matrix[0].length) {
            if ((matrix[point.x][point.y] == Block.EMPTY || matrix[point.x][point.y] == Block.APPLE)
                    && (type != Block.EMPTY && type != Block.APPLE)) {
                safeBlocks.remove(new Point(point));
            } else if ((matrix[point.x][point.y] != Block.EMPTY && matrix[point.x][point.y] != Block.APPLE)
                    && (type == Block.EMPTY || type == Block.APPLE)) {
                safeBlocks.add(new Point(point));
            }
            matrix[point.x][point.y] = type;
            ret = true;
        }
        return ret;
    }
    
    /**
     * Get the count of empty blocks on this map.
     * @return int Size of {@link com.glkwhr.snakegame.GameMap#safeBlocks}.
     */
    public int getEmptyBlockCount() {
        return safeBlocks.size();
    }
    
    /**
     * Get the row count of the map matrix.
     * @return int Row count of {@link com.glkwhr.snakegame.GameMap#matrix}.
     */
    public int getRowCount() {
        return matrix.length;
    }
    
    /**
     * Get the column count of the map matrix.
     * @return int Column count of {@link com.glkwhr.snakegame.GameMap#matrix}.
     */
    public int getColCount() {
        return matrix[0].length;
    }
    
}
