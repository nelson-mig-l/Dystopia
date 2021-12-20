package dystopia.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resources {

    public static BufferedImage loadImage(final String name) {
        final URL resource = Resources.class.getClassLoader().getResource(name);
        try {
            return ImageIO.read(resource);
        } catch (final IOException e) {
            throw new RuntimeException(name, e);
        }
    }

}
