import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Drawing extends JPanel {
    long startTime;
    double cTime;
    int width;
    int height;
    JFrame frame;
    BufferedImage image;
    BufferStrategy bufferStrategy;
    ExecutorService executorService;
    int xDiff = 0;
    int yDiff = 0;

    public Drawing(int width, int height) {
        frame = new JFrame("Drawing");
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
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public PixelInfo getPixel(int x, int y) {
        return new PixelInfo(this, x + xDiff, y + yDiff, Color.BLACK, cTime);
    }

    public void transpose(int xChange, int yChange) {
        xDiff = -xChange;
        yDiff = -yChange;
    }

    public void setAllThreaded(Class<? extends PixelFunction> functionClass, String[][] instructions) throws Exception {
        int totalTasks = width * height;
        CountDownLatch latch = new CountDownLatch(totalTasks);
        cTime = getTime();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PixelFunction function = functionClass
                    .getConstructor(PixelInfo.class, String[][].class)
                    .newInstance(getPixel(x, y), instructions)
                ;
                executorService.submit(() -> {
                    try {
                        function.run();
                    } finally {
                        latch.countDown();
                    }
                });
            }
        }
        latch.await();
    }

    public void setAll(Class<? extends PixelFunction> functionClass, String[][] instructions) throws Exception {
        cTime = getTime();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PixelFunction function = functionClass
                    .getConstructor(PixelInfo.class, String[][].class)
                    .newInstance(getPixel(x, y), instructions)
                ;
                function.run();
            }
        }
    }

    public void setPixel(int x, int y, Color color) {
        x -= xDiff;
        y -= yDiff;
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return ;
        }
        image.setRGB(x, y, color.getCode());
    }

    public double getTime() {
        return (System.nanoTime() - startTime) / 1000000;
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