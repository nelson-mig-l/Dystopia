package dystopia;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controls all navigation through the main menu
 *
 * @author Rohans
 */
public class TitleController implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Navigation through menus r-Return to menu i-how to play p-play one game
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (!TitleFrame.playing.get()) {
            switch (e.getKeyChar()) {
                case 'R':
                case 'r':
                    if (TitlePanel.screen.get() == Screens.HIGH_SCORE) {
                        TitlePanel.screen.set(Screens.TITLE);
                    }
                    break;
                case 'i':
                case 'I':
                    if (TitlePanel.screen.get() == Screens.TITLE) {
                        TitlePanel.screen.set(Screens.HELP);
                    }
                    break;

                case 'p':
                case 'P':
                    if (TitlePanel.screen.get() == Screens.TITLE || TitlePanel.screen.get() == Screens.HELP) {
                        TitlePanel.screen.set(Screens.TITLE);
                        TitleFrame.playing.set(true);
                    }
                    break;
                case 'h':
                case 'H':
                    if (TitlePanel.screen.get() == Screens.TITLE || TitlePanel.screen.get() == Screens.HELP) {
                        TitlePanel.screen.set(Screens.HIGH_SCORE);
                    }
                    break;
            }
        }
    }

}
