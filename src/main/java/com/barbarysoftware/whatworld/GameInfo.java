package com.barbarysoftware.whatworld;

public class GameInfo {

    private GameState gameState = GameState.WAITING_TO_START;
    private int score = 0;
    private long lastLooptime;
    private long thisLooptime;

    private long gameOverTime = 0;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOverTime(long gameOverTime) {
        this.gameOverTime = gameOverTime;
    }

    public long getGameOverTime() {
        return gameOverTime;
    }

    public long getLastLooptime() {
        return lastLooptime;
    }

    public void setLastLooptime(long lastLooptime) {
        this.lastLooptime = lastLooptime;
    }

    public long getThisLooptime() {
        return thisLooptime;
    }

    public void setThisLooptime(long thisLooptime) {
        this.thisLooptime = thisLooptime;
    }

}
