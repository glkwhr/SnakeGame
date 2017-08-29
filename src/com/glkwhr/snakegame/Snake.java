/**
 * 
 */
package com.glkwhr.snakegame;

import java.util.ArrayDeque;
import java.util.Deque;

import com.glkwhr.snakegame.GameMap.Block;

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
    private final Point startPoint;
    private final Dir startDir;
    private Point head;
    private Point tail;
    private Dir curDir;
    private Dir nextDir;
    
    public Snake(GameMap map, Point startPoint, Dir startDir) {
        curDir = startDir;
        this.startDir = startDir;
        this.startPoint = startPoint;
        tail = new Point(startPoint);
        head = new Point(tail.x, tail.y + 1);        
        
        body = new ArrayDeque<>();
        body.addFirst(new Point(head));
        body.addFirst(new Point(tail));
        map.setBlock(head, Block.BODY);
        map.setBlock(tail, Block.BODY);
    }
    
    public void reset(GameMap map) {
        curDir = startDir;
        nextDir = null;
        tail = new Point(startPoint);
        head = new Point(tail.x, tail.y + 1);
        
        body.clear();
        body.addFirst(new Point(head));
        body.addFirst(new Point(tail));
        map.setBlock(head, Block.BODY);
        map.setBlock(tail, Block.BODY);
    }

    public Status move(GameMap map) {
        return move(map, nextDir == null ? curDir : nextDir);
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
            case BODY:
                if (!tail.equals(nextHead)) break;
            case EMPTY:
                if (!moveTail(map)) {
                    break;
                };
                if (!moveHead(map, nextHead)) {
                    break;
                };
                status = Status.MOVE;
                break;
            case APPLE:
                map.eatApple();
                if (!moveHead(map, nextHead)) {
                    break;
                };
                map.produceApple();
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
    
    public void changeDir(Dir newDir) {
        nextDir = newDir;
    }
    
    public Dir getCurDir() {
        return curDir;
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

}
