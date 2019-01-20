package com.barbarysoftware.whatworld;

public class World {

    // todo make player start pos independent of hardcoded width/height
    private PlayerSprite playerSprite = new PlayerSprite(400, 300);

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void update(GameInfo gameInfo, long elapsed) {
        playerSprite.update(gameInfo, elapsed);
    }
}
