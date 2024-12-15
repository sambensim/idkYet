public class PixelInfo {
    public int x;
    public int y;
    public int color;
    public Drawing context;
    public double time;

    PixelInfo(int x, int y, int color, Drawing context, double time) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.context = context;
        this.time = time;
    }
}
