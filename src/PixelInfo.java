public class PixelInfo {
    public int x;
    public int y;
    public Color color;
    public Drawing context;
    public double time;

    PixelInfo(Drawing context, int x, int y) {
        this.context = context;
        this.x = x;
        this.y = y;
    }

    PixelInfo(Drawing context, int x, int y, Color color, double time) {
        this.context = context;
        this.x = x;
        this.y = y;
        this.color = color;
        this.time = time;
    }
}
