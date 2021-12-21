package dystopia.highscore;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class HighScores {

    public static HighScore[] scores;

    static {
        scores = new HighScoresFileIO().load();
    }

    public static void manageScore(int score) {
        if (score > scores[9].getScore()) {
            HighScore[] temp = new HighScore[11];
            System.arraycopy(scores, 0, temp, 0, 10);
            final String name = JOptionPane.showInputDialog("Please enter a name\n"
                    + "You got a High Score:" + score);
            temp[10] = new HighScore(name, score);
            Arrays.sort(temp);
            System.arraycopy(temp, 0, scores, 0, 10);
            try {
                new HighScoresFileIO().save(scores);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
