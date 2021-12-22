package dystopia;

import dystopia.map.Coordinate;
import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.MapTile;
import dystopia.ui.TitleFrame;

import java.util.ArrayList;
import java.util.List;

public class Cops {
    public static int bounty;
    public static List<Cop> cops = new ArrayList<>();
    public static int numCops;

    private static CopsAi ai = new CopsAi();

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
        final Coordinate position = Map.randomEmptyPosition();
        cops.add(new Cop(position, Map.getTileAt(position)));
        Map.setTileAt(position, MapTile.COP);
    }

    public static void moveAllCops() {
        ai.floodFillMap(Player.position.getX(), Player.position.getY());
        for (Cop c : cops) {
            moveSingleCop(c);
        }
    }

    private static void moveSingleCop(final Cop cop) {
        final Coordinate oldCopPosition = cop.getPosition();
        Map.setTileAt(oldCopPosition, cop.standingAt());

        final Coordinate newCopPosition = ai.getPositionFromNavigationMap(oldCopPosition);

        final Coordinate scaledCopPosition = oldCopPosition.divide(Map.MULTIPLIER);
        final MapTile whereCopIsStanding = Map.getTileAt(oldCopPosition) == MapTile.COP
                ? (Map.isNavigable(scaledCopPosition)
                    ? MapTile.SPACE
                    : MapTile.GRASS)
                : Map.getTileAt(oldCopPosition);
        cop.moveTo(newCopPosition, whereCopIsStanding);
        Map.setTileAt(newCopPosition, MapTile.COP);
        if (cop.getPosition().equals(Player.position)) {
            //todo calc score and display message
            TitleFrame.playing.set(false);
        }
    }


}
