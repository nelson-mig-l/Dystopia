package dystopia;

import dystopia.map.MapTile;

/**
 * @author Rohans
 */
public class Cop {

    private MapTile standingOn;
    private int x;
    private int y;

    public Cop(final int x, final int y, final MapTile standingOn) {
        this.x = x;
        this.y = y;
        this.standingOn = standingOn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MapTile standingAt() {
        return standingOn;
    }

    public void moveTo(final int x, final int y, final MapTile standingOn) {
        this.x = x;
        this.y = y;
        this.standingOn = standingOn;
    }

}
