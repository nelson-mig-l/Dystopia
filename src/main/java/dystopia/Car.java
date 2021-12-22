package dystopia;

import dystopia.map.Coordinate;
import dystopia.map.Map;
import dystopia.map.MapTile;
import dystopia.map.Player;

/**
 * @author Rohans
 */
public class Car {

    private Coordinate position;
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
        this.position = Map.randomEmptyPosition();
        //this.x = position.getX();
        //this.y = position.getY();
        Map.setTileAt(position, MapTile.CAR);
        final Coordinate coord = position.divide(Map.MULTIPLIER);
        if (Map.isNavigable(coord.right())) {
            dir = 0;
        } else if (Map.isNavigable(coord.up())) {
            dir = 1;
        } else if (Map.isNavigable(coord.left())) {
            dir = 2;
        } else if (Map.isNavigable(coord.down())) {
            dir = 3;
        }
    }

    public void move() {
        // free current position
        Map.setTileAt(position, MapTile.SPACE);
        double angle = Math.PI / 2 * dir;
        int dX = (int) Math.cos(angle);
        int dY = (int) Math.sin(angle);
        //if (Map.getTileAt(x + dX, y + dY) != MapTile.SPACE) {
        if (Map.getTileAt(position.add(new Coordinate(dX, dY))) != MapTile.SPACE) {
            dX *= -1;
            dY *= -1;
            dir += 2;
        }
        //if (x + dX != Player.position.getX() || y + dY != Player.position.getY()) {
        if (!Player.position.equals(position.add(new Coordinate(dX, dY)))) {
            //x += dX;
            //y += dY;
            position = position.add(new Coordinate(dX, dY));
        }
        // occupy new position
        Map.setTileAt(position, MapTile.CAR);
    }
}
