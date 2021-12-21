package dystopia;

import dystopia.ui.TitleFrame;

/**
 * @author Rohans
 */
public class Dystopia {

    public static void main(String[] args) {
        new Dystopia().run();
    }

    public void run() {
        try {
            new TitleFrame();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
