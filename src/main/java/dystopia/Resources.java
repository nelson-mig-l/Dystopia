package dystopia;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resources {

    public BufferedImage loadImage(final String name) {
        final URL url = getClass().getClassLoader().getResource(name);
        try {
            return ImageIO.read(url);
        } catch (final IOException e) {
            throw new RuntimeException(name, e);
        }
    }

}
