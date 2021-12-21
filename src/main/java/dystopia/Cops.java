package dystopia;

import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.MapTile;
import dystopia.ui.TitleFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cops {
    public static int bounty;
    public static List<Cop> cops = new ArrayList<>();
    public static int numCops;

    private static final Random random = new Random();

    public static void createCops() {
        cops = new ArrayList<>();
        numCops += bounty / 1000;
        for (int i = 0; i < numCops; i++) {
            createCop();
        }
    }

    public static void addCop() {
        numCops++;
        createCop();
    }

    private static void createCop() {
        int aX = random.nextInt(Map.TILES_SIZE);
        int aY = random.nextInt(Map.TILES_SIZE);
        while (Map.getTileAt(aX, aY) != MapTile.SPACE) {
            aX = random.nextInt(Map.TILES_SIZE);
            aY = random.nextInt(Map.TILES_SIZE);
        }
        cops.add(new Cop(aX, aY, Map.getTileAt(aX, aY)));
        Map.setTileAt(aX, aY, MapTile.COP);
    }

    public static void moveAllCops() {
        floodFillMap(Player.x, Player.y);
        for (Cop c : cops) {
            moveSingleCop(c);
        }
    }

    private static void moveSingleCop(final Cop cop) {
        int tX = cop.getX(), tY = cop.getY();
        Map.setTileAt(cop.getX(), cop.getY(), cop.standingAt());
        int shortestPath = Integer.MAX_VALUE;
        if (navigationMap[cop.getX() + 1][cop.getY()] < shortestPath) {
            tX = cop.getX() + 1;
            tY = cop.getY();
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[cop.getX()][cop.getY() + 1] < shortestPath) {
            tX = cop.getX();
            tY = cop.getY() + 1;
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[cop.getX()][cop.getY() - 1] < shortestPath) {
            tX = cop.getX();
            tY = cop.getY() - 1;
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[cop.getX() - 1][cop.getY()] < shortestPath) {
            tX = cop.getX() - 1;
            tY = cop.getY();
            shortestPath = navigationMap[tX][tY];
        }
        final MapTile whereCopIsStanding = Map.getTileAt(cop.getX(), cop.getY()) == MapTile.COP
                ? (Map.isNavigable(cop.getX() / Map.MULTIPLIER, cop.getY() / Map.MULTIPLIER)
                    ? MapTile.SPACE
                    : MapTile.GRASS)
                : Map.getTileAt(cop.getX(), cop.getY());
        cop.moveTo(tX, tY, whereCopIsStanding);
        Map.setTileAt(tX, tY, MapTile.COP);
        if (cop.getX() == Player.x && cop.getY() == Player.y) {
            //todo calc score and display message
            TitleFrame.playing.set(false);
        }
    }

    public static int[][] navigationMap;

    /**
     * We are using an integer matrix to represent the distance each spot if from
     * the player The position being attempted to be reached is one Non reachable
     * positions or walls or represented by Integer.MAX_VALUE The reset of the
     * positions represent the distance needed to travel in the maze this is to be
     * used for efficient navigation
     *
     * @param inX
     * @param inY
     * @
     */
    public static void floodFillMap(int inX, int inY) {
        //System.out.println(inX+"|"+inY);

        navigationMap = new int[Map.TILES_SIZE][Map.TILES_SIZE];
        //System.out.println(navigationMap.length+"|"+navigationMap[0].length);
        markOpen(inX, inY, 1);
        for (int i = 0; i < navigationMap.length; i++) {
            for (int j = 0; j < navigationMap[0].length; j++) {
                if (navigationMap[i][j] == 0) {
                    navigationMap[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public static void markOpen(int x, int y, int depth) {
        if (Map.inMapBounds(x, y)) {
            navigationMap[x][y] = depth;
        } else {
            return;
        }
        if (depth > 100) {
            return;
        }
        if (Map.inMapBounds(x + 1, y) && (navigationMap[x + 1][y] == 0 || depth + 1 < navigationMap[x + 1][y])
                && (Map.getTileAt(x + 1, y) == MapTile.SPACE || Map.getTileAt(x + 1, y) == MapTile.GRASS)) {
            markOpen(x + 1, y, depth + 1);
        }

        if (Map.inMapBounds(x - 1, y) && (navigationMap[x - 1][y] == 0 || depth + 1 < navigationMap[x - 1][y])
                && (Map.getTileAt(x - 1, y) == MapTile.SPACE || Map.getTileAt(x - 1, y) == MapTile.GRASS)) {
            markOpen(x - 1, y, depth + 1);
        }

        if (Map.inMapBounds(x, y + 1) && (navigationMap[x][y + 1] == 0 || depth + 1 < navigationMap[x][y + 1])
                && (Map.getTileAt(x, y + 1) == MapTile.SPACE || Map.getTileAt(x, y + 1) == MapTile.GRASS)) {
            markOpen(x, y + 1, depth + 1);
        }

        if (Map.inMapBounds(x, y - 1) && (navigationMap[x][y - 1] == 0 || depth + 1 < navigationMap[x][y - 1])
                && (Map.getTileAt(x, y - 1) == MapTile.SPACE || Map.getTileAt(x, y - 1) == MapTile.GRASS)) {
            markOpen(x, y - 1, depth + 1);
        }
    }

}
