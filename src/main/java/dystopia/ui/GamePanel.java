package dystopia.ui;

import dystopia.Cops;
import dystopia.map.Map;
import dystopia.map.Player;
import dystopia.map.MapTile;
import dystopia.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private final BufferedImage playerImage;

    public GamePanel() {
        playerImage = Resources.loadImage("Player.png");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(620, 640);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 650, 650);
        g.setColor(Color.red);
        g.drawString("Bounty: " + Cops.bounty + "$", 500, 30);
        int playerY = Player.position.getY();
        int playerX = Player.position.getX();
        for (int y = playerY - 10; y <= playerY + 10; y++) {
            for (int x = playerX + 10; x >= playerX - 10; x--) {
                BufferedImage b = MapTile.GRASS.getImage();
                if (Map.inMapBounds(x, y)) {
                    b = Map.getTileAt(x, y).getImage();
                }

                int i = x - playerX + 10;
                int j = y - playerY + 10;
                g.drawImage(b, (i + j) * 15, ((j - i) * 15) + 350 - b.getHeight(), this);
                if (x == playerX && y == playerY) {
                    i = 10;
                    j = 10;
                    g.drawImage(playerImage, (i + j) * 15 + 3, ((j - i) * 15) + 347 - playerImage.getHeight(), this);
                }
            }
        }

        g.setColor(Color.blue);
    }
}
