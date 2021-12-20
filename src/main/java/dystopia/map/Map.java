package dystopia.map;

import dystopia.Cops;
import dystopia.Game;
import dystopia.HighScore;
import dystopia.TitleFrame;

import java.util.Stack;

/**
 * Simple Maze Game
 *
 * @author rohan
 */
public class Map {

    public static final int MULTIPLIER = 5;
    public static final int CITY_SIZE = 39;

    /**
     * The boolean at maze[x][y] is whether or not that spot is open
     */
    public static boolean[][] maze;

    private static Stack<Coord> nodes = new Stack<>();
    /* This is the renderable map. */
    public static Tiles[][] map;

    public static void initialize() {
        makeCity();
        GameFrame frame = new GameFrame();
        new Thread(new Game()).start();
        while (TitleFrame.playing.get()) {
            frame.repaint();
        }
        frame.setVisible(false);
        HighScore.manageScore(Cops.bounty);
    }

    public static void makeCity() {
        Player.x = MULTIPLIER;
        Player.y = MULTIPLIER;
        maze = new boolean[CITY_SIZE][CITY_SIZE];
        nodes.push(new Coord(1, 1));
        while (!nodes.empty()) {
            findNewNode();
        }
        map = new Tiles[maze.length * MULTIPLIER][maze[0].length * MULTIPLIER];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = !maze[i / MULTIPLIER][j / MULTIPLIER] ? (!(i / MULTIPLIER == 0 || i / MULTIPLIER == CITY_SIZE - 1 || j / MULTIPLIER == 0 || j / MULTIPLIER == CITY_SIZE - 1) && Math.random() < .3 ? Tiles.GRASS : Tiles.WALL) : Tiles.SPACE;
            }
        }
        for (int i = 0; i < 350; i++) {
            int x = (int) (Math.random() * (CITY_SIZE - 2)) + 1;
            int y = (int) (Math.random() * (CITY_SIZE - 2)) + 1;
            if ((x % 2 == 1 || y % 2 == 1) && !maze[x][y]) {
                for (int j = 0; j < MULTIPLIER; j++) {
                    for (int k = 0; k < MULTIPLIER; k++) {
                        map[MULTIPLIER * x + j][MULTIPLIER * y + k] = Tiles.GRASS;
                    }
                }
            }
        }
        for (int j = 0; j <= MULTIPLIER / 2; j++)
            for (int i = 0; i <= MULTIPLIER; i++) {
                map[map.length - MULTIPLIER - 1 + i][map[0].length - MULTIPLIER - 1 - j] = Tiles.GET_AWAY;
            }
    }

    public static void findNewNode() {
        Coord c = nodes.peek();
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
                if (!maze[attemptX][attemptY]) {
                    maze[attemptX][attemptY] = true;
                    maze[x + ((attemptX - x) / 2)][y + ((attemptY - y) / 2)] = true;
                    nodes.push(new Coord(attemptX, attemptY));
                    return;
                }
            }
            startCase += 1;
            startCase %= 4;
        }
        nodes.pop();
    }

    public static boolean inMapBounds(int x, int y) {
        return x > 0 && y > 0 && x < map.length && y < map[x].length;
    }

    private static boolean inMazeBounds(int x, int y) {
        return x > 0 && y > 0 && x < maze.length && y < maze[x].length;
    }
}
