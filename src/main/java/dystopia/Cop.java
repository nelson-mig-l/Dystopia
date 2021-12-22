package dystopia;

import dystopia.map.Coordinate;
import dystopia.map.MapTile;

/**
 * @author Rohans
 */
public class Cop {

    private MapTile standingOn;
    private Coordinate position;
    private int y;

    public Cop(final Coordinate position, final MapTile standingOn) {
        this.position = position;
        this.standingOn = standingOn;
    }

    public Coordinate getPosition() {
        return position;
    }

    public MapTile standingAt() {
        return standingOn;
    }

    public void moveTo(final Coordinate position, final MapTile standingOn) {
        this.position = position;
        this.standingOn = standingOn;
    }

}
