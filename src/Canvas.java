import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    long startTime;
    int width;
    int height;
    JFrame frame;
    BufferedImage image;
    BufferStrategy bufferStrategy;

    public Canvas(int width, int height) {
        frame = new JFrame("Pixel Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.width = width;
        this.height = height;
        startTime = System.nanoTime();
        frame.setSize(width, height);
        frame.add(this);
        frame.setVisible(true);
        frame.createBufferStrategy(2); // Create a double buffer strategy
        bufferStrategy = frame.getBufferStrategy();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void setRGB(int x, int y, int red, int green, int blue) {
        int color = (red << 16) | (green << 8) | blue;
        image.setRGB(x, y, color);
    }

    public void setBW(int x, int y, int brightness) {
        int color = (brightness << 16) | (brightness << 8) | brightness;
        image.setRGB(x, y, color);
    }

    public long getTime() {
        return System.nanoTime() - startTime;
    }

    public void update() {
        render();
    }

    //I don't really understand the following two methods
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void render() {
        Graphics g = bufferStrategy.getDrawGraphics();
        paintComponent(g);
        g.dispose();
        bufferStrategy.show();
    }
}