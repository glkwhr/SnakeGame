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
    
    // default block size (in pixel)
    public static final int DEFAULT_BLOCK_SIZE = 30;
    
    // default map size (in pixel)
    public static final int DEFAULT_MAP_WIDTH = 210;
    public static final int DEFAULT_MAP_HEIGHT = 210;
    
    // default map size (block number)
    public static final int DEFAULT_ROW = 5;
    public static final int DEFAULT_COL = 5;

    public static final Point START_POINT = new Point(3, 3);

    public static final Dir START_DIR = Dir.RIGHT;

    public static final long DEFAULT_MOVE_INTERVAL = 500; // ms
    
}
