package dystopia.map;

import dystopia.utils.Resources;

import java.awt.image.BufferedImage;

public enum MapTile {

    GRASS("Grass.png"),
    SPACE("Empty.png"),
    WALL("Wall.png"),
    CAR("Car.png"),
    COP("Cop.png"),
    GET_AWAY("getAway.png");

    private final BufferedImage image;

    MapTile(String resource) {
        this.image = Resources.loadImage(resource);
    }

    public BufferedImage getImage() {
        return image;
    }

}
