package com.barbarysoftware.whatworld;

import java.awt.*;

public class PlayerSprite {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

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
        g.fillOval(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);

    }
}
