package dystopia;

import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.MapTile;

import java.util.Random;

/**
 * @author Rohans
 */
public class Car {

    private int x;
    private int y;
    /**
     * Represents the direction this car is traveling
     *   3
     *   I
     * 2-O-0
     *   I
     *   1
     */
    private int dir;

    public Car() {
        final Random random = new Random();
        int xAttempt = random.nextInt(Map.TILES_SIZE);
        int yAttempt = random.nextInt(Map.TILES_SIZE);
        while (Map.getTileAt(xAttempt,yAttempt) != MapTile.SPACE) {
            xAttempt = random.nextInt(Map.TILES_SIZE);
            yAttempt = random.nextInt(Map.TILES_SIZE);
        }
        x = xAttempt;
        y = yAttempt;
        Map.setTileAt(x, y, MapTile.CAR);
        if (Map.isNavigable(x / Map.MULTIPLIER + 1, y / Map.MULTIPLIER)) {
            dir = 0;
        } else if (Map.isNavigable(x / Map.MULTIPLIER, y / Map.MULTIPLIER + 1)) {
            dir = 1;
        } else if (Map.isNavigable(x / Map.MULTIPLIER - 1, y / Map.MULTIPLIER)) {
            dir = 2;
        } else if (Map.isNavigable(x / Map.MULTIPLIER, y / Map.MULTIPLIER + 1)) {
            dir = 1;
        }
    }

    public void move() {
        // free current position
        Map.setTileAt(x, y, MapTile.SPACE);
        double angle = Math.PI / 2 * dir;
        int dX = (int) Math.cos(angle);
        int dY = (int) Math.sin(angle);
        if (Map.getTileAt(x + dX, y + dY) != MapTile.SPACE) {
            dX *= -1;
            dY *= -1;
            dir += 2;
        }
        if (x + dX != Player.x || y + dY != Player.y) {
            x += dX;
            y += dY;
        }
        // occupy new position
        Map.setTileAt(x, y, MapTile.CAR);
    }
}
