/**
 * 
 */
package com.glkwhr.snakegame;

import java.util.Scanner;

import com.glkwhr.snakegame.GameMap.Block;
import com.glkwhr.snakegame.Snake.Dir;
import com.glkwhr.snakegame.Snake.Status;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class SnakeGame {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(2, 8, new Point(1, 1), Dir.RIGHT);
        snakeGame.gameStart();
    }

    private int score;
    private final GameMap map;
    private final Snake snake;
    private final Scanner scanner;
    
    public SnakeGame(int row, int col, Point startPoint, Dir startDir) {
        score = 0;
        map = new GameMap(row, col);
        snake = new Snake(map, startPoint, startDir);
        scanner = new Scanner(System.in);
        map.setBlock(new Point(1, 4), Block.APPLE);
    }
    
    private void gameStart() {
        do {
            show();
            try {   
                Thread.sleep(500);//∫¡√Î   
            }   
            catch(Exception e){
                
            }
        } while (snake.move(map, convertInput()) != Status.DEAD);
        gameOver();
    }
    
    private Dir convertInput() {
        Dir ret = snake.getCurDir();;
        
        return ret;
    }

    private void gameOver() {
        System.out.printf("**********************\n");
        System.out.printf("****  GAME OVER  *****\n");
        System.out.printf("**** SCORE:%6d ****\n", score);
        System.out.printf("**********************\n");
    }

    private void show() {
        for (int i = 0; i < map.getRowCount(); i++) {
            for (int j = 0; j < map.getColCount(); j++) {
                switch (map.getBlock(i, j)) {
                case EMPTY:
                    System.out.printf("  "); break;
                case WALL:
                    System.out.printf("# "); break;
                case APPLE:
                    System.out.printf("* "); break;
                case BODY:
                    System.out.printf("O "); break;
                default:
                    break;
                }
            }
            System.out.printf("\n");
        }
    }

}
