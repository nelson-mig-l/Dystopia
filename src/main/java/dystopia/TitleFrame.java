package dystopia;

import dystopia.map.Map;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rohans
 */
public class TitleFrame extends JFrame {

    public static AtomicBoolean playing = new AtomicBoolean(false);
    private static final long serialVersionUID = 1L;

    public static TitleFrame t;

    public static void initialize() throws Exception {
        t = new TitleFrame();
        while (true) {
            while (!playing.get()) {
                t.repaint();
            }
            t.setVisible(false);
            Map.initialize();
            while (playing.get()) {
                Thread.sleep(10);
            }
            t.setVisible(true);
        }
    }

    public TitleFrame() {
        super("Dystopia");
        TitlePanel p = new TitlePanel();
        this.add(p);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new TitleController());
        this.setLocationRelativeTo(null);
        this.repaint();
    }

}
