package com.barbarysoftware.whatworld;

import java.awt.*;

public class PlayerSprite {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private double x;
    private double y;

    private double targetX;
    private double targetY;


    public PlayerSprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);

    }

    public void moveTo(int x, int y) {
        targetX = x;
        targetY = y;
    }

    public void update(GameInfo gameInfo) {

        long delta = gameInfo.getTimeDelta();

        double speed = 50d;

        if (x < targetX) {
            x += delta / speed;
            if (x > targetX) x = targetX;
        }

        if (y < targetY) {
            y += delta / speed;
            if (y > targetY) y = targetY;
        }

        if (x > targetX) {
            x -= delta / speed;
            if (x < targetX) x = targetX;
        }

        if (y > targetY) {
            y -= delta / speed;
            if (y < targetY) y = targetY;
        }

    }
}
