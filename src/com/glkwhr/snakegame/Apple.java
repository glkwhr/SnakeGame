package com.glkwhr.snakegame;

import java.util.Random;

import com.glkwhr.snakegame.GameMap.Block;

/**
 * Apple class controls the behavior of the apple in the game.
 * @author H.W.
 * @since   Aug 28, 2017   
 */
public class Apple {
    
    /**
     * The location of the apple (It should be null if the apple has not been produced yet).
     */
    private Point pos;
    
    /**
     * The seed used to generate new location of the apple.
     */
    private final Random rand;
    
    /**
     * The constructor only initializes the random seed. Use produce() to generate an apple.
     */
    public Apple() {
        rand = new Random();        
    }
    
    /**
     * The method is used to get the current location of the apple
     * @return Point This returns the current location of the apple in (row, col). 
     */
    public Point getPos() {
        return new Point(pos);
    }
    
    /**
     * The method is used to produce an apple.
     * @param map The map on which the apple would be placed.
     * @return Point This returns the location of the produced apple in (row, col). 
     */
    public Point produce(GameMap map) {
        Point ret = null;
        if (map.getEmptyBlockCount() > 0) {
            pos = (Point) map.getSafeBlocks().toArray()[rand.nextInt(map.getSafeBlocks().size())];
            map.setBlock(pos, Block.APPLE);
            ret = new Point(pos);
        }
        return ret;
    }
    
    /**
     * The method is used to consume the apple.
     * @param map The map where the apple is.
     * @return Boolean Whether the apple in the map is consumed.
     */
    public Boolean consume(GameMap map) {
        Point oldPos = new Point(pos);
        pos = null;
        return map.setBlock(oldPos, Block.EMPTY);
    }
}
