import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Drawing extends JPanel {
    long startTime;
    int width;
    int height;
    JFrame frame;
    BufferedImage image;
    BufferStrategy bufferStrategy;
    ExecutorService executorService;

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

    public void setAllThreaded(Class<? extends PixelFunction> functionClass) throws Exception {
        int totalTasks = width * height;
        CountDownLatch latch = new CountDownLatch(totalTasks);
        double cTime = getTime();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PixelInfo pixelInfo = new PixelInfo(x, y, 0, this, cTime);
                PixelFunction function = functionClass
                    .getConstructor(PixelInfo.class)
                    .newInstance(pixelInfo)
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

        public void setAll(Class<? extends PixelFunction> functionClass) throws Exception {
            double cTime = getTime();
    
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    PixelInfo pixelInfo = new PixelInfo(x, y, 0, this, cTime);
                    PixelFunction function = functionClass
                        .getConstructor(PixelInfo.class)
                        .newInstance(pixelInfo)
                    ;
                    function.run();
                }
            }
    }

    public void setPixel(int x, int y, Color color) {
        image.setRGB(x, y, color.getCode());
    }

    public void rect(int x1, int y1, int x2, int y2, Color color) {
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                setPixel(x, y, color);
            }
        }
    }

    public void fill(Color color) {
        rect(0, 0, width, height, color);
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