package dystopia.map;

import dystopia.Cops;
import dystopia.Game;
import dystopia.ui.GameFrame;
import dystopia.ui.TitleFrame;
import dystopia.highscore.HighScores;

import java.util.Random;
import java.util.Stack;

/**
 * Simple Maze Game
 *
 * @author rohan
 */
public class Map {

    public static final int MULTIPLIER = 5;
    public static final int CITY_SIZE = 39;
    public static final int TILES_SIZE = CITY_SIZE * MULTIPLIER;

    private static final Random random = new Random();

    public static Coordinate randomEmptyPosition() {
        int x = random.nextInt(Map.TILES_SIZE);
        int y = random.nextInt(Map.TILES_SIZE);
        while (Map.getTileAt(x, y) != MapTile.SPACE) {
            x = random.nextInt(Map.TILES_SIZE);
            y = random.nextInt(Map.TILES_SIZE);
        }
        return new Coordinate(x, y);
    }

    private static boolean[][] navigable;

    private static MapTile[][] tiles;

    private static final Stack<Coordinate> nodes = new Stack<>();

    public static void initialize() {
        makeCity();
        GameFrame frame = new GameFrame();
        new Thread(new Game()).start();
        while (TitleFrame.playing.get()) {
            frame.repaint();
        }
        frame.setVisible(false);
        HighScores.manageScore(Cops.bounty);
    }

    @Deprecated
    public static MapTile getTileAt(int x, int y) {
        return tiles[x][y];
    }
    public static MapTile getTileAt(final Coordinate position) {
        return tiles[position.getX()][position.getY()];
    }

    @Deprecated
    public static void setTileAt(int x, int y, MapTile tile) {
        tiles[x][y] = tile;
    }
    public static void setTileAt(Coordinate position, MapTile tile) {
        tiles[position.getX()][position.getY()] = tile;
    }

    public static void makeCity() {
        Player.position = new Coordinate(MULTIPLIER, MULTIPLIER);
        navigable = new boolean[CITY_SIZE][CITY_SIZE];
        nodes.push(new Coordinate(1, 1));
        while (!nodes.empty()) {
            findNewNode();
        }
        tiles = new MapTile[navigable.length * MULTIPLIER][navigable[0].length * MULTIPLIER];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = !navigable[i / MULTIPLIER][j / MULTIPLIER] ? (!(i / MULTIPLIER == 0 || i / MULTIPLIER == CITY_SIZE - 1 || j / MULTIPLIER == 0 || j / MULTIPLIER == CITY_SIZE - 1) && Math.random() < .3 ? MapTile.GRASS : MapTile.WALL) : MapTile.SPACE;
            }
        }
        for (int i = 0; i < 350; i++) {
            int x = (int) (Math.random() * (CITY_SIZE - 2)) + 1;
            int y = (int) (Math.random() * (CITY_SIZE - 2)) + 1;
            if ((x % 2 == 1 || y % 2 == 1) && !navigable[x][y]) {
                for (int j = 0; j < MULTIPLIER; j++) {
                    for (int k = 0; k < MULTIPLIER; k++) {
                        tiles[MULTIPLIER * x + j][MULTIPLIER * y + k] = MapTile.GRASS;
                    }
                }
            }
        }
        for (int j = 0; j <= MULTIPLIER / 2; j++)
            for (int i = 0; i <= MULTIPLIER; i++) {
                tiles[tiles.length - MULTIPLIER - 1 + i][tiles[0].length - MULTIPLIER - 1 - j] = MapTile.GET_AWAY;
            }
    }

    private static void findNewNode() {
        Coordinate c = nodes.peek();
        int x = c.getX();
        int y = c.getY();

        int startCase = (int) (Math.random() * 4);

        for (int i = 0; i < 4; i++) {
            int attemptX = x, attemptY = y;
            switch (startCase) {
                case 0:
                    attemptX += 2;
                    break;
                case 1:
                    attemptX -= 2;
                    break;
                case 2:
                    attemptY += 2;
                    break;
                case 3:
                    attemptY -= 2;
                    break;
            }
            if (inMazeBounds(attemptX, attemptY)) {
                if (!navigable[attemptX][attemptY]) {
                    navigable[attemptX][attemptY] = true;
                    navigable[x + ((attemptX - x) / 2)][y + ((attemptY - y) / 2)] = true;
                    nodes.push(new Coordinate(attemptX, attemptY));
                    return;
                }
            }
            startCase += 1;
            startCase %= 4;
        }
        nodes.pop();
    }

    public static boolean isNavigable(final Coordinate position) {
        return navigable[position.getX()][position.getY()];
    }

    public static boolean inMapBounds(int x, int y) {
        return x > 0 && y > 0 && x < tiles.length && y < tiles[x].length;
    }

    private static boolean inMazeBounds(int x, int y) {
        return x > 0 && y > 0 && x < navigable.length && y < navigable[x].length;
    }
}
