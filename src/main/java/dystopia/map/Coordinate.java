package dystopia.map;

public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate up() {
        return new Coordinate(x, y + 1);
    }

    public Coordinate down() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate left() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate right() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate divide(final int value) {
        return new Coordinate(x / value, y / value);
    }

    public Coordinate add(final Coordinate coordinate) {
        return new Coordinate(x + coordinate.x, y + coordinate.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
