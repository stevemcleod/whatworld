package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    private Canvas canvas;

    private GameInfo gameInfo = new GameInfo();
    private World world = new World();

    private MouseEvent currentMouseStatus;

    private Main() {
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        new Main().createAndStart();

    }

    private void createAndStart() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            try {
                createAndShowGui();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        long lastLooptime = System.currentTimeMillis();
        gameInfo.setLastLooptime(lastLooptime);

        long start = System.currentTimeMillis();
        int desiredFrameRate = 120; // fps
        long millisPerUpdate = 1000 / desiredFrameRate;
        System.out.println("millisPerUpdate = " + millisPerUpdate);

        long previous = System.currentTimeMillis();

        long frameCount = 0;
        while (true) {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;

            gameInfo.setThisLooptime(current);

            processInput();
            update(elapsed);
            render();

            frameCount++;
            if (frameCount % 100 == 0) {
                long totalSeconds = (current - start) / 1000;
                if (totalSeconds > 0) {
                    long fps = frameCount / totalSeconds;
                    System.out.println("fps = " + fps);
                }
            }

            long sleepTime = current + millisPerUpdate - System.currentTimeMillis();
            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
            gameInfo.setLastLooptime(current);
        }

    }

    private void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("What World");
        canvas = new Canvas();
        canvas.setGameInfo(gameInfo);
        canvas.setWorld(world);

        // catch any mouse clicks on canvas
        canvas.addMouseListener(new MouseAdapter() {
            // todo find a more performant way to do this
            @Override
            public void mousePressed(MouseEvent e) {
                currentMouseStatus = e;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentMouseStatus = null;
            }
        });

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // start main loop

    }

    private void update(long elapsed) {
        world.update(gameInfo, elapsed);
    }

    private void processInput() {
        // convert property to local, for thread-safety
        MouseEvent status = this.currentMouseStatus;
        if (status != null) {

            // copy to local for thread-safety
            final GameInfo currentGameInfo = gameInfo;

            final GameState gameState = currentGameInfo.getGameState();
            switch (gameState) {

                case WAITING_TO_START:
                    currentGameInfo.setGameState(GameState.PLAYING);
                    break;

                case PLAYING:

                    int worldX = status.getX();
                    int worldY = status.getY();

                    int tile = world.getTileAt(worldX, worldY);
                    if (tile == 1) {
                        // can't move onto wall
                    } else {
                        world.getPlayerSprite().moveTo(worldX, worldY);

                    }

                    break;

                case GAME_OVER:
                    long millisSinceGameOver = currentGameInfo.getThisLooptime() - currentGameInfo.getGameOverTime();
                    if (millisSinceGameOver > 1000) {
                        world.getPlayerSprite().reset();
                        currentGameInfo.setGameState(GameState.PLAYING);
                    }
                    break;
            }
        }
    }

    private void render() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> canvas.repaint());
    }

}
