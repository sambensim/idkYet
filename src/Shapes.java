public class Shapes {
    Drawing context;

    Shapes(Drawing context) {
        this.context = context;
    }

    public void rect(int x1, int y1, int x2, int y2, Color color) {
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                context.setPixel(x, y, color);
            }
        }
    }

    public void circle(int xx, int yy, int radius, Color color) {
        int x1 = xx - radius;
        int y1 = yy - radius;
        int x2 = xx + radius;
        int y2 = yy + radius;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (Math.sqrt(Math.pow(x - xx, 2) + Math.pow(y - yy, 2)) < radius) {
                    context.setPixel(x, y, color);
                }
            }
        }
    }

    public void fill(Color color) {
        rect(0 + context.xDiff, 0 + context.yDiff, context.width + context.xDiff, context.height + context.yDiff, color);
    }
}
