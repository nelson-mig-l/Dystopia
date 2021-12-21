package dystopia.ui;

import dystopia.map.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Player.move(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
