package software.ulpgc.image_viewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {

        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();

    }

    private BufferedImage load(String name) {

        try {

            return ImageIO.read(new File(name));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Image image() {

        return image;

    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
        int x = (this.getWidth() - resized.width) / 2;
        int y = (this.getHeight() - resized.height) / 2;
        g.drawImage(bitmap, x, y, resized.width, resized.height, null);

    }

    public static class Resizer {
        private final Dimension dimension;

        public Resizer(Dimension dimension) {

            this.dimension = dimension;

        }

        public Dimension resize(Dimension original) {

            int width = original.width;
            int height = original.height;

            int max_width = dimension.width;
            int max_height = dimension.height;

            int newWidth = 0;
            int newHeight = 0;

            if ((width < max_width) || (width >= max_width)) {

                newWidth = max_width;
                newHeight = (newWidth * height) / width;

            }

            if ((height < max_height) || (height >= max_height)) {

                newHeight = max_height;
                newWidth = (newHeight * width) / height;

            }

            return new Dimension(newWidth, newHeight);

        }
    }
}