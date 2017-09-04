package com.glkwhr.snakegame;

import java.util.ArrayDeque;
import java.util.Deque;

import com.glkwhr.snakegame.GameMap.Block;

/**
 * Snake class contains information and behavior of the snake.
 * @author H.W.
 * @since   Aug 28, 2017   
 */
public class Snake {
    
    /**
     * Moving directions.
     */
    public static enum Dir {
        UP, RIGHT, DOWN, LEFT
    }
    
    /** 
     * Snake status.
     */
    public static enum Status {
        MOVE, EAT, DEAD, ERROR
    }
    
    /**
     * Get the opposite direction of given direction.
     * @param curDir Given direction.
     * @return Dir The opposite direction.
     */
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
    
    /**
     * Computes the next point's location after moving given point towards given direction by 1 block.
     * @param head Given point.
     * @param curDir Given direction.
     * @return Point Location of the next point.
     */
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
    
    /**
     * Double ended queue that stores the points' locations of the snake. [tail, body, ..., body, head]
     */
    private final Deque<Point> body;
    
    /**
     * Original start point given when creating the snake.
     */
    private final Point startPoint;
    
    /**
     * Original moving direction given when creating the snake.
     */
    private final Dir startDir;
    
    /**
     * Current location of the snake's head block.
     */
    private Point head;
    
    /**
     * Current location of the snake's tail block.
     */
    private Point tail;
    
    /**
     * Current moving direction.
     */
    private Dir curDir;
    
    /**
     * Direction given by the player.
     */
    private Dir nextDir;
    
    /**
     * Initiate a snake object.
     * @param map The game map where the snake should be.
     * @param startPoint Original start point.
     * @param startDir Original moving direction.
     */
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
    
    /**
     * Reset the snake data and put it on the given map.
     * @param map Given map where the snake should be.
     */
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

    /**
     * Move the snake on given game map.
     * @param map Given game map.
     * @return Status Status of the snake after the move.
     */
    public Status move(GameMap map) {
        return move(map, nextDir == null ? curDir : nextDir);
    }
    
    /**
     * Move the snake towards given direction on given game map.
     * @param map Given game map.
     * @param dir Given moving direction.
     * @return Status Status of the snake after the move.
     */
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
    
    /**
     * Try to change current direction to new direction.
     * @param newDir New direction.
     */
    public void changeDir(Dir newDir) {
        nextDir = newDir;
    }
    
    /**
     * Get current moving direction.
     * @return Dir Current moving direction.
     */
    public Dir getCurDir() {
        return curDir;
    }

    /**
     * Move snake's tail on given map.
     * @param map Given map.
     * @return boolean Whether the tail is moved.
     */
    private boolean moveTail(GameMap map) {
        Point oldTail = new Point(tail);
        body.removeFirst();
        tail.x = body.peekFirst().x;
        tail.y = body.peekFirst().y;
        return map.setBlock(oldTail, Block.EMPTY);
    }

    /**
     * Move snake's head to given point on given map.
     * @param map Given map.
     * @param nextHead Given destination point.
     * @return boolean Whether the head is moved.
     */
    private boolean moveHead(GameMap map, Point nextHead) {
        body.addLast(new Point(nextHead));
        head.x = nextHead.x;
        head.y = nextHead.y;
        return map.setBlock(nextHead, Block.BODY);        
    }

}
