/**
 * 
 */
package com.glkwhr.snakegame;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class Point {
    
    public int x; // row
    public int y; // column
    
    public Point() {
        this(0, 0);
    }
    
    public Point(Point p) {
        this(p.x, p.y);
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
