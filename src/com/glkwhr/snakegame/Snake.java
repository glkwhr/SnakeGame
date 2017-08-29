/**
 * 
 */
package com.glkwhr.snakegame;

import java.util.ArrayDeque;
import java.util.Deque;

import com.glkwhr.snakegame.GameMap.Block;
import com.glkwhr.snakegame.Snake.Dir;

import javafx.scene.shape.MoveTo;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class Snake {
    
    // directions
    public static enum Dir {
        UP, RIGHT, DOWN, LEFT
    }
    
    // status after move()
    public static enum Status {
        MOVE, EAT, DEAD, ERROR
    }
    
    public static Dir opposite(Dir curDir) {
        Dir ret = Dir.UP;
        switch (curDir) {
        case UP: 
            ret = Dir.DOWN; break;
        case RIGHT:
            ret = Dir.LEFT; break;
        case LEFT:
            ret = Dir.RIGHT; break;
        default:
            break;
        }        
        return ret;        
    }
    
    public static Point movePoint(Point head, Dir curDir) {
        Point newPoint = head;
        switch(curDir) {
        case UP:
            newPoint.x -= 1; break;
        case DOWN:
            newPoint.x += 1; break;
        case LEFT:
            newPoint.y -= 1; break;
        default:
            newPoint.y += 1; break;
        }
        return newPoint;
    }
    
    private final Deque<Point> body; // [tail, body, ..., body, head]
    private Point head;
    private Point tail;
    private Dir curDir;
    
    public Snake(GameMap map, Point startPoint, Dir startDir) {
        curDir = startDir;
        head = new Point();
        tail = new Point();
        
        /*if (startPoint.x < 2) {
            tail.x = 1;
            head.x = 2;
        } else {
            if (startPoint.x >= map.getRowCount() - 1) {
                head.x = map.getRowCount() - 2;
            } else {
                head.x = startPoint.x;
            }
            tail.x = head.x - 1;
        }*/
        tail.x = startPoint.x;
        head.x = tail.x;
        if (startPoint.y < 2) {
            tail.y = 1;
            head.y = 2;
        } else { 
            if (startPoint.y >= map.getColCount() - 1) {
                head.y = map.getColCount() - 2;
            } else {
                head.y = startPoint.y;
            }
            tail.y = head.y - 1;
        }
        
        body = new ArrayDeque<>();
        body.addFirst(new Point(head));
        body.addFirst(new Point(tail));
        map.setBlock(head, Block.BODY);
        map.setBlock(tail, Block.BODY);
    }

    public Status move(GameMap map) {
        return move(map, curDir);
    }
    /* 
     * move towards current direction 
     * */
    public Status move(GameMap map, Dir dir) {
        Status status = Status.ERROR;
        if (dir != opposite(curDir)) {
            curDir = dir;
        }

        Point nextHead = movePoint(head, curDir);
        Block nextBlock = map.getBlock(nextHead);
        if (nextBlock != Block.ERROR) {
            switch (nextBlock) {
            case EMPTY:
                if (!moveHead(map, nextHead)) {
                    break;
                };
                if (!moveTail(map)) {
                    break;
                };
                status = Status.MOVE;
                break;
            case APPLE:
                map.eatApple();
                if (!moveHead(map, nextHead)) {
                    break;
                };
                status = Status.EAT;
                break;
            case WALL:
                status = Status.DEAD;
                break;
            default: 
                break;
            }
        }
        return status;
    }

    private boolean moveTail(GameMap map) {
        Point oldTail = new Point(tail);
        body.removeFirst();
        tail.x = body.peekFirst().x;
        tail.y = body.peekFirst().y;
        return map.setBlock(oldTail, Block.EMPTY);
    }

    private boolean moveHead(GameMap map, Point nextHead) {
        body.addLast(new Point(nextHead));
        head.x = nextHead.x;
        head.y = nextHead.y;
        return map.setBlock(nextHead, Block.BODY);        
    }

    public Dir getCurDir() {
        return curDir;
    }

}
