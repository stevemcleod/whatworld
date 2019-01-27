package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private static final int TILE_WIDTH = 48;
    private static final int TILE_HEIGHT = 48;

    private static final int WIDTH = 20 * TILE_WIDTH;
    private static final int HEIGHT = 15 * TILE_HEIGHT;

    private GameInfo gameInfo;
    private World world;
    private Tiles tiles = Tiles.getInstance();

    Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {

        drawFloor(g);

        switch (gameInfo.getGameState()) {
            case WAITING_TO_START:
                paintWaitingToStart(g);
                break;
            case PLAYING:
                paintPlaying(g);
                break;
            case GAME_OVER:
                paintGameOver(g);
                break;
        }

    }

    private void drawFloor(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        int[][] floor = world.getFloor();
        for (int y = 0; y < floor.length; y++) {
            int[] row = floor[y];
            for (int x = 0; x < row.length; x++) {

                int tileType = row[x];
                tiles.draw(g, tileType, x * TILE_WIDTH, y * TILE_HEIGHT, x * TILE_WIDTH + 48, y * TILE_HEIGHT + 48);
            }
        }
    }

    private void paintWaitingToStart(Graphics g) {
        drawInstructions(g, "Welcome to What World", "Now with tiles and black jack", "Click anywhere to start");
    }

    private void drawInstructions(Graphics g, String heading, String subheading, String line3) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 40));
        int sw = g.getFontMetrics().stringWidth(heading);
        int sh = g.getFontMetrics().getHeight();
        g.drawString(heading, (getWidth() - sw) / 2, (getHeight() - sh) / 2);

        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        int sw2 = g.getFontMetrics().stringWidth(subheading);
        int sh2 = g.getFontMetrics().getHeight();
        g.drawString(subheading, (getWidth() - sw2) / 2, (int) ((getHeight() - sh) * 0.55));

        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        int sw3 = g.getFontMetrics().stringWidth(line3);
        int sh3 = g.getFontMetrics().getHeight();
        g.drawString(line3, (getWidth() - sw3) / 2, (int) ((getHeight() - sh3) * 0.65));

    }

    private void paintPlaying(Graphics g) {
        g.setColor(Color.WHITE);
        world.getPlayerSprite().paint(g);
    }

    private void paintGameOver(Graphics g) {
        boolean showRestartMessage = gameInfo.getThisLooptime() - gameInfo.getGameOverTime() > 1000;
        drawInstructions(g, gameInfo.getGameResult(), "", showRestartMessage ? "Click anywhere to restart" : "");
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
