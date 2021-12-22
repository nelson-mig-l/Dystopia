package dystopia;

import dystopia.map.Coordinate;
import dystopia.map.Map;
import dystopia.map.MapTile;

public class CopsAi {

    private int[][] navigationMap;

    public Coordinate getPositionFromNavigationMap(final Coordinate cop) {
        int shortestPath = Integer.MAX_VALUE;
        int tX = cop.getX();;
        int tY = cop.getY();

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
        return new Coordinate(tX, tY);
    }

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
    public void floodFillMap(int inX, int inY) {
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

    private void markOpen(int x, int y, int depth) {
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
