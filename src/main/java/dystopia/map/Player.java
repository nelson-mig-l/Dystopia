package dystopia.map;

import dystopia.Cops;
import dystopia.Game;

public class Player {

    public static int x = Map.MULTIPLIER, y = Map.MULTIPLIER;

    public static void move(char m) {
        int tX = x, tY = y;
        switch (m) {
            case 'w':
                tY--;
                break;
            case 'a':
                tX--;
                break;
            case 's':
                tY++;
                break;
            case 'd':
                tX++;
                break;
        }
        if (Map.tiles[tX][tY] == Tiles.GET_AWAY) {
            Map.makeCity();
            Game.makeCars();
            Cops.createCops();
            Cops.bounty += 25000;
        }
        if (Map.inMapBounds(tX, tY) && Map.tiles[tX][tY] == Tiles.SPACE || Map.tiles[tX][tY] == Tiles.GRASS) {
            x = tX;
            y = tY;
        }
    }
}