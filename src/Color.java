public class Color {
    static final Color BLACK = new Color(0);
    static final Color WHITE = new Color(255);
    static final Color RED = new Color(255, 0, 0);
    static final Color BLUE = new Color(0, 255, 0);
    static final Color GREEN = new Color(0, 0, 255);

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

    public static Color fromCode(int code) { //idk why the "& 0xFF" is needed
        return new Color((code >> 16) & 0xFF, (code >> 8) & 0xFF, code & 0xFF);
    }
}
