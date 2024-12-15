public class PixelMath extends PixelFunction {
    public PixelMath(PixelInfo pixel) {
        super(pixel);
    }

    public void run() {
        int x = pixel.x;
        int y = pixel.y;
        int color = (int) Math.round(pixel.time) % 256;
        pixel.context.setPixel(x, y, new Color(color));
    }
}