package dystopia;

import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.MapTile;

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

        int xAttempt = (int) (Math.random() * Map.tiles.length);
        int yAttempt = (int) (Math.random() * Map.tiles[0].length);
        while (Map.tiles[xAttempt][yAttempt] != MapTile.SPACE) {
            xAttempt = (int) (Math.random() * Map.tiles.length);
            yAttempt = (int) (Math.random() * Map.tiles[0].length);
        }
        x = xAttempt;
        y = yAttempt;
        Map.tiles[x][y] = MapTile.CAR;
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
        Map.tiles[x][y] = MapTile.SPACE;
        double angle = Math.PI / 2 * dir;
        int dX = (int) Math.cos(angle);
        int dY = (int) Math.sin(angle);
        if (Map.tiles[x + dX][y + dY] != MapTile.SPACE) {
            dX *= -1;
            dY *= -1;
            dir += 2;
        }
        if (x + dX != Player.x || y + dY != Player.y) {
            x += dX;
            y += dY;
        }
        Map.tiles[x][y] = MapTile.CAR;
    }
}
