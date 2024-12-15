public abstract class PixelFunction implements Runnable {
    PixelInfo pixel;

    public PixelFunction(PixelInfo pixelInfo) {
        this.pixel = pixelInfo;
    }

    public abstract void run();
}