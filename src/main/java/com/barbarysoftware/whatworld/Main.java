package com.barbarysoftware.whatworld;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class Main {

    private Canvas canvas;

    private GameInfo gameInfo = new GameInfo();
    private World world = new World();

    private MouseEvent currentMouseStatus;


    public Main() {
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        new Main().createAndStart();

    }

    private void createAndStart() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(this::createAndShowGui);

        long lastLooptime = System.currentTimeMillis();
        gameInfo.setLastLooptime(lastLooptime);

        while (true) {
            gameInfo.setThisLooptime(System.currentTimeMillis());
            processInput();
            update();
            render();
            gameInfo.setLastLooptime(gameInfo.getThisLooptime());
        }

    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("What World");
        canvas = new Canvas();
        canvas.setGameInfo(gameInfo);
        canvas.setWorld(world);

        // catch any mouseclicks on canvas
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

    private void update() {
        world.update(gameInfo);
        // todo
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
                    world.getPlayerSprite().moveTo(status.getX(), status.getY());

                    int score = currentGameInfo.getScore();
                    score += (currentGameInfo.getThisLooptime() - currentGameInfo.getLastLooptime());
                    currentGameInfo.setScore(score);
                    if (score >= 5_000) {
                        currentGameInfo.setGameState(GameState.GAME_OVER);
                        currentGameInfo.setGameOverTime(currentGameInfo.getThisLooptime());
                    }
                    break;
                case GAME_OVER:
                    if (currentGameInfo.getThisLooptime() - currentGameInfo.getGameOverTime() > 1000) {
                        currentGameInfo.setScore(0);
                        currentGameInfo.setGameState(GameState.PLAYING);
                    }
                    break;
            }
        }
    }

    private void render() {
        canvas.repaint();
    }

}
