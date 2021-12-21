package dystopia.map;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("Dystopia");
        this.add(new GamePanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                final int vkSpace = KeyEvent.VK_SPACE;
                Player.move(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}