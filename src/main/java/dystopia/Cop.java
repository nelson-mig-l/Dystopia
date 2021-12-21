package dystopia;

import dystopia.map.Tiles;

/**
 * @author Rohans
 */
public class Cop {

    public Tiles standingOn;
    private int x;
    private int y;

    public Cop(final int x, final int y, final Tiles standingOn) {
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

    public void moveTo(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

}
