package dystopia.highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * @author rohan
 */
public class HighScoresFileIO {

    private static final String HIGH_SCORE_FILE = "highscore.txt";

    public HighScore[] load() {
        try {
            return Files.readAllLines(Path.of(HIGH_SCORE_FILE)).stream()
                    .map(line -> line.split(","))
                    .map(tokens -> new HighScore(tokens[0], Integer.parseInt(tokens[1])))
                    .toArray(HighScore[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(HighScore[] highScores) throws FileNotFoundException {
        File file = new File(HIGH_SCORE_FILE);
        try (final PrintWriter writer = new PrintWriter(file)) {
            Arrays.stream(highScores).forEach(hs ->
                    writer.println(hs.getName() + "," + hs.getScore())
            );
            writer.println();
            writer.flush();
        }
    }

}
