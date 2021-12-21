package dystopia.ui;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("Dystopia");
        this.add(new GamePanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addKeyListener(new GameController());
    }
}