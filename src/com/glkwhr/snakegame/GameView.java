/**
 * 
 */
package com.glkwhr.snakegame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class GameView {
    
    private final GameMap map;
    private JPanel canvas;

    public GameView(GameMap map) {
        this.map = map;
    }
    
    public void init() {
        canvas = new JPanel() {
            
            private static final long serialVersionUID = 7036254525837907298L;

            @Override
            public void paintComponent(Graphics graphics) {        
                for (int i = 0; i < map.getRowCount(); i++) {
                    for (int j = 0; j < map.getColCount(); j++) {
                        switch (map.getBlock(i, j)) {
                        case WALL:
                            drawWall(graphics, i, j); break;
                        case APPLE:
                            drawApple(graphics, i, j); break;
                        case BODY:
                            drawSnake(graphics, i, j); break;
                        default:
                            drawBackground(graphics, i, j); break;
                        }
                    }
                }
            }
        };
    }

    public void draw() {
        canvas.repaint();
    }
    
    public JPanel getCanvas() {
        return canvas;
    }

    private void drawWall(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.BLACK);
    }

    private void drawSnake(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.ORANGE);
    }

    private void drawApple(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.GRAY);
        drawCircle(graphics, row, col, Color.RED);
    }

    private void drawBackground(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.GRAY);
    }
    
    private void drawSquare(Graphics graphics, int row, int col, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_BLOCK_SIZE;
        graphics.fillRect(col * size, row * size, size - 1, size - 1);
    }

    private void drawCircle(Graphics graphics, int row, int col, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_BLOCK_SIZE;
        graphics.fillOval(col * size, row * size, size - 1, size - 1);
    }

}
