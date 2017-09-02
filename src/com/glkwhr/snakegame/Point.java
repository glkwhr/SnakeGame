package com.glkwhr.snakegame;

/**
 * Point class stores the coordinates of blocks.
 * @author H.W.
 * @since   Aug 28, 2017   
 */
public class Point {
    
    /**
     * Row number.
     */
    public int x;
    
    /**
     * Column number.
     */
    public int y;
    
    /**
     * Create an new Point object without giving coordinates.
     */
    public Point() {
        this(0, 0);
    }
    
    /**
     * Create an new Point object using existing point.
     * @param p
     */
    public Point(Point p) {
        this(p.x, p.y);
    }
    
    /**
     * Create an new Point object using given coordinates.
     * @param x
     * @param y
     */
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
