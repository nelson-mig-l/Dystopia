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
        for (int y = Player.y - 10; y <= Player.y + 10; y++) {
            for (int x = Player.x + 10; x >= Player.x - 10; x--) {
                BufferedImage b = MapTile.GRASS.getImage();
                if (Map.inMapBounds(x, y)) {
                    b = Map.tiles[x][y].getImage();
                }

                int i = x - Player.x + 10;
                int j = y - Player.y + 10;
                g.drawImage(b, (i + j) * 15, ((j - i) * 15) + 350 - b.getHeight(), this);
                if (x == Player.x && y == Player.y) {
                    i = 10;
                    j = 10;
                    g.drawImage(playerImage, (i + j) * 15 + 3, ((j - i) * 15) + 347 - playerImage.getHeight(), this);
                }
            }
        }

        g.setColor(Color.blue);
    }
}
