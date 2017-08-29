/**
 * 
 */
package com.glkwhr.snakegame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.glkwhr.snakegame.Snake.Dir;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class SnakeGame implements Runnable {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SnakeGame(Settings.DEFAULT_ROW, Settings.DEFAULT_COL, Settings.START_POINT, Settings.START_DIR));
    }

    private final GameMap map;
    private final Snake snake;
    private GameView gameView;
    private GameController gameController;
    
    public SnakeGame(int row, int col, Point startPoint, Dir startDir) {
        map = new GameMap(row, col);
        snake = new Snake(map, startPoint, startDir);
        map.produceApple();
    }
    
    @Override
    public void run() {
        JFrame window = new JFrame("Snake Game");
        
        Container contentPane = window.getContentPane();

        gameView = new GameView(map);
        gameView.init();
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_MAP_WIDTH, Settings.DEFAULT_MAP_HEIGHT));        
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        
        gameController = new GameController(snake, map, gameView);
        window.addKeyListener(gameController);
        
        window.pack();
        window.setResizable(false);
        window.setLocation(500, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        new Thread(gameController).start();
    }

}
