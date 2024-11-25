public class Color {
    int red, green, blue;

    Color(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    Color(int brightness) {
        red = brightness;
        green = brightness;
        blue = brightness;
    }

    public int getCode() {
        return (red << 16) | (green << 8) | blue;
    }
}
