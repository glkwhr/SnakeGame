package com.glkwhr.snakegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.glkwhr.snakegame.Snake.Dir;
import com.glkwhr.snakegame.Snake.Status;

/**
 * GameController class receives user input and controls the game.
 * @author H.W.
 * @since   Aug 29, 2017   
 */
public class GameController implements Runnable, KeyListener {
    
    /**
     * Snake object in the game.
     */
    private final Snake snake;
    
    /**
     * GameMap used in the game.
     */
    private final GameMap map;
    
    /**
     * Game GUI.
     */
    private final GameView gameView;
    
    /**
     * Indicates whether the game is still running.
     */
    private boolean running;
    
    /**
     * Indicates the status of the snake.
     */
    private Status status;
    
    /**
     * Indicates current score.
     */
    private int score;
    
    /**
     * Creates a new GameController object.
     * @param snake The snake in this game.
     * @param map The game map in this game.
     * @param gameView Game GUI.
     */
    public GameController(Snake snake, GameMap map, GameView gameView) {
        this.snake = snake;
        this.map = map;
        this.gameView = gameView;
        this.status = Status.MOVE;
        this.running = true;
        score = 0;
    }
    
    /**
     * Handles key events. Redraws the {@link com.glkwhr.snakegame.GameController#gameView} when finished.
     */
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
        case KeyEvent.VK_F2:
            reset();
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

    /**
     * Game control in the child thread.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (running) {
                if (status != Status.DEAD && status != Status.ERROR) {
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
                } else {
                    gameOver();
                }
            }
        }
    }
    
    /**
     * Reset game data and restart.
     */
    private void reset() {
        map.reset();
        snake.reset(map);
        map.produceApple();
        score = 0;
        status = Status.MOVE;
        running = true;
    }
    
    /**
     * This method would be called when the {@link com.glkwhr.snakegame.GameController#snake} is {@link com.glkwhr.snakegame.Snake.Status#DEAD} or {@link com.glkwhr.snakegame.Snake.Status#ERROR}
     */
    private void gameOver() {
        running = false;
        String result = "Game Over: You ";
        if (map.getEmptyBlockCount() == 0) {
            result += "Win!";
        } else {
            result += "Lose!";
        }
        JOptionPane.showMessageDialog(null, "Score: " + score, result, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
