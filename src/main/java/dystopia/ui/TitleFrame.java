package dystopia.ui;

import dystopia.map.Map;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rohans
 */
public class TitleFrame extends JFrame {

    public static AtomicBoolean playing = new AtomicBoolean(false);

    public TitleFrame() {
        super("Dystopia");
        this.add(new TitlePanel());
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new TitleController());
        this.setLocationRelativeTo(null);
        this.repaint();

        start();
    }

    private void start() {
        while (true) {
            while (!playing.get()) {
                this.repaint();
            }
            this.setVisible(false);
            Map.initialize();
            while (playing.get()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignore) { }
            }
            this.setVisible(true);
        }
    }

}
