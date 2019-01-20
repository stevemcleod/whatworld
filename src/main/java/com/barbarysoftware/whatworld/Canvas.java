package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MY_WIDTH = 10;
    private static final int MY_HEIGHT = 10;

    private PlayerSprite playerSprite = new PlayerSprite(WIDTH / 2, HEIGHT / 2);

    Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                moveMe(e.getX(), e.getY());
            }
        });
    }

    private void moveMe(int x, int y) {
        playerSprite.setX(x);
        playerSprite.setY(y);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawString("Hello What World", 20, 20);
        playerSprite.paint(g);
    }

    public static class PlayerSprite {
        private int x;
        private int y;

        public PlayerSprite(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void paint(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x - MY_WIDTH / 2, y - MY_HEIGHT / 2, MY_WIDTH, MY_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawOval(x - MY_WIDTH / 2, y - MY_HEIGHT / 2, MY_WIDTH, MY_HEIGHT);

        }
    }
}
