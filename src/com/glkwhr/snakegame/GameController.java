/**
 * 
 */
package com.glkwhr.snakegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.glkwhr.snakegame.Snake.Dir;
import com.glkwhr.snakegame.Snake.Status;

/**
 * @author H.W.
 * @date   Aug 29, 2017   
 */
public class GameController implements Runnable, KeyListener {
    
    private final Snake snake;
    private final GameMap map;
    private final GameView gameView;
    private Status status;
    private int score;
    
    public GameController(Snake snake, GameMap map, GameView gameView) {
        this.snake = snake;
        this.map = map;
        this.gameView = gameView;
        this.status = Status.MOVE;
        score = 0;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_UP:
            snake.changeDir(Dir.UP);
            break;
        case KeyEvent.VK_DOWN:
            snake.changeDir(Dir.DOWN);
            break;
        case KeyEvent.VK_LEFT:
            snake.changeDir(Dir.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            snake.changeDir(Dir.RIGHT);
            break;

        default:
            break;
        }
        
        gameView.draw();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        do {
            gameView.draw();
            if (status == Status.EAT) {
                score += 10;
            }
            try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
            status = snake.move(map);
        } while (status != Status.DEAD && status != Status.ERROR);
        gameOver();
    }
    
    private void gameOver() {
        String result = "Game Over: You ";
        if (map.getEmptyBlockCount() == 0) {
            result += "Win!";
        } else {
            result += "Lose!";
        }
        JOptionPane.showMessageDialog(null, "Score: " + score, result, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
