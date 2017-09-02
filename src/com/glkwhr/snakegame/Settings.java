package com.glkwhr.snakegame;

import com.glkwhr.snakegame.Snake.Dir;

/**
 * Settings of the game.
 * @author H.W.
 * @since   Aug 29, 2017   
 */
public class Settings {    
    /** 
     * Default map row count (blocks).
     */
    public static final int DEFAULT_ROW = 6;
    /** 
     * Default map col count (blocks).
     */
    public static final int DEFAULT_COL = 8;    
    /** 
     * Default block size (in pixel).
     */
    public static final int DEFAULT_BLOCK_SIZE = 50;    
    /** 
     * Default map width (in pixel).
     */
    public static final int DEFAULT_MAP_WIDTH = (Settings.DEFAULT_COL + 2) * Settings.DEFAULT_BLOCK_SIZE - Settings.DEFAULT_BLOCK_SIZE / 2;
    /** 
     * Default map height (in pixel).
     */
    public static final int DEFAULT_MAP_HEIGHT = (Settings.DEFAULT_ROW + 2) * Settings.DEFAULT_BLOCK_SIZE - Settings.DEFAULT_BLOCK_SIZE / 2;
    /**
     * Default start point of the snake.
     */
    public static final Point START_POINT = new Point(DEFAULT_ROW / 2, DEFAULT_COL / 2);
    /**
     * Default start moving direction of the snake. 
     */
    public static final Dir START_DIR = Dir.RIGHT;
    /**
     * Default move interval (ms) of the snake.
     */
    public static final long DEFAULT_MOVE_INTERVAL = 400;
    
}
