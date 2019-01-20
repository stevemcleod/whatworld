package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class Canvas extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int clickCount = 0;
    private boolean gameOver = false;

    private PlayerSprite playerSprite = new PlayerSprite(WIDTH / 2, HEIGHT / 2);

    Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) {
                    clickCount = 0;
                    gameOver = false;
                } else {
                    moveMe(e.getX(), e.getY());
                    clickCount++;
                    if (clickCount >= 10) {
                        gameOver = true;
                    }
                }

                repaint();

            }
        });
    }

    private void moveMe(int x, int y) {
        playerSprite.setX(x);
        playerSprite.setY(y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        String str;
        if (gameOver) {
            str = "You won! Congratulations. Click anywhere to restart.";

        } else {
            if (clickCount > 0) {
                str = "Score: " + NumberFormat.getNumberInstance().format(clickCount * 1000);
            } else {
                str = "Welcome to What World.";

            }

        }
        g.drawString(str, 20, 20);
        playerSprite.paint(g);

    }

}
