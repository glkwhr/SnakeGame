/**
 * 
 */
package com.glkwhr.snakegame;

import java.util.Random;
import java.util.Set;

import com.glkwhr.snakegame.GameMap.Block;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class Apple {
    
    private Point pos;
    private final Random rand;
    
    public Apple() {
        rand = new Random();        
    }
    
    public Point getPos() {
        return new Point(pos);
    }
    
    public Point produce(GameMap map) {
        Point ret = null;
        if (map.getEmptyBlockCount() > 0) {
            pos = (Point) map.getSafeBlocks().toArray()[rand.nextInt(map.getSafeBlocks().size())];
            map.setBlock(pos, Block.APPLE);
            ret = new Point(pos);
        }
        return ret;
    }
    
    public Boolean consume(GameMap map) {
        Point oldPos = new Point(pos);
        pos = null;
        return map.setBlock(oldPos, Block.EMPTY);
    }
}
