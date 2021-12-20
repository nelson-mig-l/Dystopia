package dystopia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author rohan
 */
public class HighScoreFile {

    /**
     * @param fileName is the path to the file or just the name if it is local
     * @return the number of lines in fileName
     */
    public static int getLengthOfFile(String fileName) {
        int length = 0;
        try {
            File textFile = new File(fileName);
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                sc.nextLine();
                length++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return length;
    }

    /**
     * @param fileName is the path to the file or just the name if it is local
     * @return an array of Strings where each string is one line from the file
     * fileName.
     */
    public static String[] getWordsFromFile(String fileName) {
        int lengthOfFile = getLengthOfFile(fileName);
        String[] wordBank = new String[lengthOfFile];
        int i = 0;
        try {
            File textFile = new File(fileName);
            Scanner sc = new Scanner(textFile);
            for (i = 0; i < lengthOfFile; i++) {
                wordBank[i] = sc.nextLine();
            }
            return wordBank;
        } catch (Exception e) {
            System.err.println(e);
            System.exit(55);
        }
        return null;
    }

    /**
     * Writes a variable number of high scores to a file
     *
     * @param fileName the file locally to write to
     * @param scores   the high score object(s)
     */
    public static void writeHighScoresToFile(String fileName, HighScore... scores) {

        BufferedWriter output = null;
        try {
            File aFile = new File(fileName);
            FileWriter myWriter = new FileWriter(aFile);
            output = new BufferedWriter(myWriter);
            for (HighScore stuff : scores) {
                output.write(stuff.toString());
                output.newLine();
            }
            output.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
