package com.barbarysoftware.whatworld;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tiles {

    private final static Tiles INSTANCE = new Tiles();
    private Image tileset;

    private Tiles() {
        try {
            tileset = ImageIO.read(getClass().getResource("/0x72_DungeonTilesetII_v1.2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Tiles getInstance() {
        return INSTANCE;
    }

    public Rectangle getTileDimensions(int tileType) {
        switch (tileType) {
            case 0:
                // passable floor
                return new Rectangle(16, 64, 16, 16);
            case 1:
                // wall
                return new Rectangle(16, 16, 16, 16);
            case 2:
                // deadly lava
                return new Rectangle(64, 16, 16, 16);
            case 3:
                // ladder
                return new Rectangle(48, 96, 16, 16);
            case 4:
                // fearless hero
                return new Rectangle(128, 36, 16, 28);
            default:
                throw new RuntimeException("Unknown tileType: " + tileType);
        }
    }

    public void draw(Graphics g, int tileType, int x, int y, int x2, int y2) {
        Rectangle tileDimensions = getTileDimensions(tileType);
        g.drawImage(tileset, x, y, x2, y2, tileDimensions.x, tileDimensions.y, tileDimensions.x + tileDimensions.width, tileDimensions.y + tileDimensions.height, null);
    }

}
