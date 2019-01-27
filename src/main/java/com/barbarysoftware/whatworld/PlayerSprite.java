package com.barbarysoftware.whatworld;

import java.awt.*;

public class PlayerSprite {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private double x;
    private double y;

    private Tiles tiles = Tiles.getInstance();


    public PlayerSprite() {
        reset();
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
        // 128, 36, 16, 28
        tiles.draw(g, 4, (int) x, (int) y - 28 * 3, (int) x + 16 * 3, (int) y);

    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(World world, GameInfo gameInfo, long elapsed) {
        if (gameInfo.getGameState() != GameState.PLAYING) {
            return;
        }

        int tile = world.getTileAt(x, y);

        if (tile == 2) {
            gameInfo.setGameState(GameState.GAME_OVER);
            gameInfo.setGameOverTime(gameInfo.getThisLooptime());
            gameInfo.setGameResult("You burnt to death!");

        } else if (tile == 3) {
            gameInfo.setGameState(GameState.GAME_OVER);
            gameInfo.setGameOverTime(gameInfo.getThisLooptime());
            gameInfo.setGameResult("You escaped to the Black Jack room!");

        }

    }

    public void reset() {
        x = 400;
        y = 300;
    }
}
