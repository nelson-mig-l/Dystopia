package dystopia;

/**
 * @author Rohans
 */
public class Dystopia {

    public static void main(String[] args) {
        new Dystopia().run();
    }

    public void run() {
        try {
            TitleFrame.initialize();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
