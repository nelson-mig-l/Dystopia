package dystopia.map;

import dystopia.Cops;
import dystopia.Game;

public class Player {

    public static Coordinate position = new Coordinate(Map.MULTIPLIER, Map.MULTIPLIER);

    public static void move(char m) {
        int tX = position.getX(), tY = position.getY();
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
        if (Map.getTileAt(tX, tY) == MapTile.GET_AWAY) {
            Map.makeCity();
            Game.makeCars();
            Cops.createCops();
            Cops.bounty += 25000;
        }
        if (Map.inMapBounds(tX, tY) && Map.getTileAt(tX, tY) == MapTile.SPACE || Map.getTileAt(tX, tY) == MapTile.GRASS) {
            //x = tX;
            //y = tY;
            position = new Coordinate(tX, tY);
        }
    }
}