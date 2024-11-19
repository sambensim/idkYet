import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Canvas extends JPanel {
    long duration;

    private BufferedImage image;

    public PixelDrawing(long duration) {
        this.duration = duration;
        image = new BufferedImage(1800, 1200, BufferedImage.TYPE_INT_RGB);
        drawPixels();
    }

    private void drawPixels() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int red = 0;
                int blue = 0;
                int green = 0;
                int brightness = 0;
                brightness = (int) duration % 256;
                red = brightness;
                blue = brightness;
                green = brightness;
                int color = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, color);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static JFrame init(int width, int height) {
        JFrame frame = new JFrame("Pixel Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        long startTime = System.nanoTime();
        return frame;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        while (true) {
            long duration = (long) Math.round((System.nanoTime() - startTime) / 10000000.0);
            if (duration % 1 == 0) {
                frame.add(new PixelDrawing(duration));
                frame.setVisible(true);
            }
        }
    }
}