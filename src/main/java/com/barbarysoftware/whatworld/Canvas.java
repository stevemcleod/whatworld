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
    private GameState gameState = GameState.WAITING_TO_START;

    private PlayerSprite playerSprite = new PlayerSprite(WIDTH / 2, HEIGHT / 2);

    Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (gameState) {

                    case WAITING_TO_START:
                        clickCount = 0;
                        gameState = GameState.PLAYING;
                        break;

                    case PLAYING:
                        moveMe(e.getX(), e.getY());
                        clickCount++;
                        if (clickCount >= 10) {
                            gameState = GameState.GAME_OVER;
                        }
                        break;

                    case GAME_OVER:
                        clickCount = 0;
                        gameState = GameState.PLAYING;
                        break;
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

        switch (gameState) {
            case WAITING_TO_START:
                doWaitingToStartPaint(g);
                break;
            case PLAYING:
                doPlayingPaint(g);
                break;
            case GAME_OVER:
                doGameOverPaint(g);
                break;
        }

    }

    private void doWaitingToStartPaint(Graphics g) {
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

    private void doPlayingPaint(Graphics g) {
        g.setColor(Color.WHITE);
        String str;
        str = "Score: " + NumberFormat.getNumberInstance().format(clickCount * 1000);
        g.drawString(str, 20, 20);
        playerSprite.paint(g);
    }

    private void doGameOverPaint(Graphics g) {
        drawInstructions(g, "You won! Congratulations.", "Click anywhere to restart");
    }

}
