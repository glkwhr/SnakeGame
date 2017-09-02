package com.glkwhr.snakegame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * GameView class controls game GUI.
 * @author H.W.
 * @date   Aug 28, 2017   
 */
public class GameView {
    
    /**
     * Map of the game.
     */
    private final GameMap map;
    
    /**
     * The canvas where the game map (including snake and apple) is drawn.
     */
    private JPanel canvas;

    /**
     * Create an GameView of given game map.
     * @param map The map to draw.
     */
    public GameView(GameMap map) {
        this.map = map;
    }
    
    /**
     * Initiate the canvas.
     */
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

    /**
     * This method is used to draw/redraw the map blocks.
     */
    public void draw() {
        canvas.repaint();
    }
    
    /**
     * Get the canvas.
     * @return JPanel {@link com.glkwhr.snakegame.GameView#canvas}.
     */
    public JPanel getCanvas() {
        return canvas;
    }

    /**
     * This method is used to draw blocks of the type {@link com.glkwhr.snakegame.GameMap.Block#WALL}.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     */
    private void drawWall(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.BLACK);
    }

    /**
     * This method is used to draw blocks of the type {@link com.glkwhr.snakegame.GameMap.Block#SNAKE}.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     */
    private void drawSnake(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.ORANGE);
    }

    /**
     * This method is used to draw blocks of the type {@link com.glkwhr.snakegame.GameMap.Block#APPLE}.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     */
    private void drawApple(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.GRAY);
        drawCircle(graphics, row, col, Color.RED);
    }

    /**
     * This method is used to draw blocks of the type {@link com.glkwhr.snakegame.GameMap.Block#EMPTY}.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     */
    private void drawBackground(Graphics graphics, int row, int col) {
        drawSquare(graphics, row, col, Color.GRAY);
    }
    
    /**
     * This method is used to draw an square on given block using given color.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     * @param color Color of the square.
     */
    private void drawSquare(Graphics graphics, int row, int col, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_BLOCK_SIZE;
        graphics.fillRect(col * size, row * size, size - 1, size - 1);
    }

    /**
     * This method is used to draw an circle on given block using given color.
     * @param graphics
     * @param row Row number of the block.
     * @param col Column number of the block.
     * @param color Color of the circle.
     */
    private void drawCircle(Graphics graphics, int row, int col, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_BLOCK_SIZE;
        graphics.fillOval(col * size, row * size, size - 1, size - 1);
    }

}
