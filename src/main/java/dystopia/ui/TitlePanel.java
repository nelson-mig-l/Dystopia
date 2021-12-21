package dystopia.ui;

import dystopia.highscore.HighScore;
import dystopia.highscore.HighScores;
import dystopia.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

public class TitlePanel extends JPanel {

    /**
     * These dots are to be used to separate the names from the scores in the high
     * score table.
     */
    private static final String DOTS = ".......................................";

    /**
     * Shows what screen is currently showing
     */
    public static AtomicReference<TitleScreens> screen = new AtomicReference<>(TitleScreens.TITLE);

    private static final BufferedImage titleScreenImage;
    private static final BufferedImage helpScreenImage;
    private static final BufferedImage highScoreBackground;

    private static final Font highScoreFont = new Font("Monospaced", Font.PLAIN, 24);

    static {
        highScoreBackground = Resources.loadImage("HighScore.png");
        titleScreenImage = Resources.loadImage("Title.png");
        helpScreenImage = Resources.loadImage("Title.png");
    }

    public TitlePanel() {
        TitlePanel.screen.set(TitleScreens.TITLE);
        this.setBackground(Color.black);
    }

    @Override
    public Dimension getPreferredSize() {
        int h = 640;
        return new Dimension(h, h);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (screen.get()) {
            case TITLE:
                g.drawImage(titleScreenImage, 0, 0, null);
                break;
            case HELP:
                g.drawImage(helpScreenImage, 0, 0, null);
                g.setColor(Color.red);
                g.setFont(new Font("Times New Roman Bold", Font.BOLD, 12));
                g.drawString("Use WASD to get to the back alley in the bottom corner and make it to the next city.", 0, 640 - 12);
                break;
            case HIGH_SCORE:
                g.drawImage(highScoreBackground, 0, 0, null);
                final Color c = new Color(255, 10, 10);
                g.setColor(c);
                g.setFont(highScoreFont);
                g.drawString("Most Wanted", 250, 50);
                int pixelsFromTop = 100;
                for (HighScore s : HighScores.scores) {
                    g.drawString(s.getName() + DOTS.substring(s.getName().length()), 0, pixelsFromTop);
                    g.drawString(s.getScore() + "", 550, pixelsFromTop);
                    pixelsFromTop += 50;

                }
                g.drawString("Press \"R\" to return to the home screen", 0, 640 - 12);
        }
    }
}