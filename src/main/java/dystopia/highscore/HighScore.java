package dystopia.highscore;

/**
 * Structure for managing high scores as well as updating the table
 *
 * @author rohan
 */
public class HighScore implements Comparable<HighScore> {

    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighScore h) {
        return -1 * (this.score - h.score);
    }

}
