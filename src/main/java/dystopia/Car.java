package dystopia;

import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.Tiles;

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
    public int dir;

    /**
     * Creates a car at a given location
     */
    private Car(int x, int y) {
        if (!Map.inMapBounds(x, y)) {
            throw new RuntimeException("Something bad happened while creating car");
        } else {
            this.x = x;
            this.y = y;
        }
        Map.map[x][y] = Tiles.CAR;
        dir = 0;
    }

    public Car() {

        int xAttempt = (int) (Math.random() * Map.map.length);
        int yAttempt = (int) (Math.random() * Map.map[0].length);
        while (Map.map[xAttempt][yAttempt] != Tiles.SPACE) {
            xAttempt = (int) (Math.random() * Map.map.length);
            yAttempt = (int) (Math.random() * Map.map[0].length);
        }
        x = xAttempt;
        y = yAttempt;
        Map.map[x][y] = Tiles.CAR;
        if (Map.maze[(x / Map.MULTIPLIER) + 1][(y / Map.MULTIPLIER)]) {
            dir = 0;
        } else if (Map.maze[(x / Map.MULTIPLIER)][(y / Map.MULTIPLIER) + 1]) {
            dir = 1;
        } else if (Map.maze[(x / Map.MULTIPLIER) - 1][(y / Map.MULTIPLIER)]) {
            dir = 2;
        } else if (Map.maze[(x / Map.MULTIPLIER)][(y / Map.MULTIPLIER) + 1]) {
            dir = 1;
        }
    }

    public void move() {
        Map.map[x][y] = Tiles.SPACE;
        double angle = Math.PI / 2 * dir;
        int dX = (int) Math.cos(angle);
        int dY = (int) Math.sin(angle);
        if (Map.map[x + dX][y + dY] != Tiles.SPACE) {
            dX *= -1;
            dY *= -1;
            dir += 2;
        }
        if (x + dX != Player.x || y + dY != Player.y) {
            x += dX;
            y += dY;
        }
        Map.map[x][y] = Tiles.CAR;
    }
}
