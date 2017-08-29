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
    
    @Override
    public boolean equals(Object o) {
        boolean ret = false;
        if (this == o) {
            ret = true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Point other = (Point) o;
            ret = this.x == other.x && this.y == other.y;
        }
        return ret;
    }
    
    @Override
    public int hashCode() {
        int hash = 13 * x;
        hash = 13 * hash + y;
        return hash;
    }
}
