/**
 * 
 */
package com.glkwhr.snakegame;

import com.glkwhr.snakegame.Snake.Dir;

/**
 * @author H.W.
 * @date   Aug 29, 2017   
 */
public class Settings {
    
    // default map size (block number)
    public static final int DEFAULT_ROW = 6;
    public static final int DEFAULT_COL = 8;
    
    // default block size (in pixel)
    public static final int DEFAULT_BLOCK_SIZE = 50;
    
    // default map size (in pixel)
    public static final int DEFAULT_MAP_WIDTH = (Settings.DEFAULT_COL + 2) * Settings.DEFAULT_BLOCK_SIZE - Settings.DEFAULT_BLOCK_SIZE / 2;
    public static final int DEFAULT_MAP_HEIGHT = (Settings.DEFAULT_ROW + 2) * Settings.DEFAULT_BLOCK_SIZE - Settings.DEFAULT_BLOCK_SIZE / 2;
    
    

    public static final Point START_POINT = new Point(DEFAULT_ROW / 2, DEFAULT_COL / 2);

    public static final Dir START_DIR = Dir.RIGHT;

    public static final long DEFAULT_MOVE_INTERVAL = 400; // ms
    
}
