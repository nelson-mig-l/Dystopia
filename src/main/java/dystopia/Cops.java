package dystopia;

import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.Tiles;

import java.util.ArrayList;

public class Cops {
    public static int bounty;
    public static ArrayList<Cop> cops = new ArrayList<>();
    public static int numCops;

    public static void createCops() {
        cops = new ArrayList<>();
        numCops += bounty / 1000;
        for (int i = 0; i < numCops; i++) {
            int aX = (int) (Math.random() * Map.tiles.length);
            int aY = (int) (Math.random() * Map.tiles[0].length);
            while (Map.tiles[aX][aY] != Tiles.SPACE) {
                aX = (int) (Math.random() * Map.tiles.length);
                aY = (int) (Math.random() * Map.tiles[0].length);
            }
            cops.add(new Cop(aX, aY, Map.tiles[aX][aY]));
            Map.tiles[aX][aY] = Tiles.COP;
        }
    }

    public static void addCop() {
        numCops++;
        int aX = (int) (Math.random() * Map.tiles.length);
        int aY = (int) (Math.random() * Map.tiles[0].length);
        while (Map.tiles[aX][aY] != Tiles.SPACE) {
            aX = (int) (Math.random() * Map.tiles.length);
            aY = (int) (Math.random() * Map.tiles[0].length);
        }
        cops.add(new Cop(aX, aY, Map.tiles[aX][aY]));
        Map.tiles[aX][aY] = Tiles.COP;
    }

    public static void moveAllCops() {
        floodFillMap(Player.x, Player.y);
        for (Cop c : cops) {
            moveSingleCop(c);
        }
    }

    private static void moveSingleCop(final Cop c) {
        int tX = c.getX(), tY = c.getY();
        Map.tiles[c.getX()][c.getY()] = c.standingOn;
        int shortestPath = Integer.MAX_VALUE;
        if (navigationMap[c.getX() + 1][c.getY()] < shortestPath) {
            tX = c.getX() + 1;
            tY = c.getY();
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[c.getX()][c.getY() + 1] < shortestPath) {
            tX = c.getX();
            tY = c.getY() + 1;
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[c.getX()][c.getY() - 1] < shortestPath) {
            tX = c.getX();
            tY = c.getY() - 1;
            shortestPath = navigationMap[tX][tY];
        }
        if (navigationMap[c.getX() - 1][c.getY()] < shortestPath) {
            tX = c.getX() - 1;
            tY = c.getY();
            shortestPath = navigationMap[tX][tY];
        }
        c.moveTo(tX, tY);
        c.standingOn = Map.tiles[c.getX()][c.getY()] == Tiles.COP
                ? (Map.isNavigable(c.getX() / Map.MULTIPLIER, c.getY() / Map.MULTIPLIER)
                    ? Tiles.SPACE
                    : Tiles.GRASS)
                : Map.tiles[c.getX()][c.getY()];
        Map.tiles[tX][tY] = Tiles.COP;
        if (c.getX() == Player.x && c.getY() == Player.y) {
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

        navigationMap = new int[Map.tiles.length][Map.tiles[0].length];
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
        if (Map.inMapBounds(x + 1, y) && (navigationMap[x + 1][y] == 0 || depth + 1 < navigationMap[x + 1][y]) && (Map.tiles[x + 1][y] == Tiles.SPACE || Map.tiles[x + 1][y] == Tiles.GRASS)) {
            markOpen(x + 1, y, depth + 1);
        }

        if (Map.inMapBounds(x - 1, y) && (navigationMap[x - 1][y] == 0 || depth + 1 < navigationMap[x - 1][y]) && (Map.tiles[x - 1][y] == Tiles.SPACE || Map.tiles[x - 1][y] == Tiles.GRASS)) {
            markOpen(x - 1, y, depth + 1);
        }

        if (Map.inMapBounds(x, y + 1) && (navigationMap[x][y + 1] == 0 || depth + 1 < navigationMap[x][y + 1]) && (Map.tiles[x][y + 1] == Tiles.SPACE || Map.tiles[x][y + 1] == Tiles.GRASS)) {
            markOpen(x, y + 1, depth + 1);
        }

        if (Map.inMapBounds(x, y - 1) && (navigationMap[x][y - 1] == 0 || depth + 1 < navigationMap[x][y - 1]) && (Map.tiles[x][y - 1] == Tiles.SPACE || Map.tiles[x][y - 1] == Tiles.GRASS)) {
            markOpen(x, y - 1, depth + 1);
        }
    }

}
