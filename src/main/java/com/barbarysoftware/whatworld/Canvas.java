package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class Canvas extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private PlayerSprite playerSprite = new PlayerSprite(WIDTH / 2, HEIGHT / 2);
    private GameInfo gameInfo;

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

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

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

    private void paintWaitingToStart(Graphics g) {
        drawInstructions(g, "Welcome to What World", "Click anywhere to start");
    }

    private void drawInstructions(Graphics g, String text, String subtext) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 40));
        int sw = g.getFontMetrics().stringWidth(text);
        int sh = g.getFontMetrics().getHeight();
        g.drawString(text, (getWidth() - sw) / 2, (getHeight() - sh) / 2);

        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        int sw2 = g.getFontMetrics().stringWidth(subtext);
        g.drawString(subtext, (getWidth() - sw2) / 2, (int) ((getHeight() - sh) / 2 + sh * 1.2));
    }

    private void paintPlaying(Graphics g) {
        g.setColor(Color.WHITE);
        String str;
        str = "Score: " + NumberFormat.getNumberInstance().format(gameInfo.getScore());
        g.drawString(str, 20, 20);
        playerSprite.paint(g);
    }

    private void paintGameOver(Graphics g) {
        long l = (System.currentTimeMillis() / 10) % 360;
        double v = Math.sin(Math.toRadians(l));
        int v1 = (int) (v * 50);


        g.setColor(new Color(205 + v1, 0, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
        String subtext;
        if (gameInfo.getThisLooptime() - gameInfo.getGameOverTime() > 1000) {
            subtext = "Click anywhere to restart";
        } else {
            subtext = "";
        }
        drawInstructions(g, "You won! Congratulations.", subtext);
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}
