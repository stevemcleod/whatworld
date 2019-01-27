package com.barbarysoftware.whatworld;

public class GameInfo {

    private GameState gameState = GameState.WAITING_TO_START;
    private long lastLooptime;
    private long thisLooptime;

    private String gameResult;

    private long gameOverTime = 0;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }
}
