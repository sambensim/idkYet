import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Drawing drawing = new Drawing(width, height);
        drawing.fill(new Color(0));
        while (true) {
            drawing.fill(new Color(((int) drawing.getTime() / 10000000) % 256));
            drawing.update();
        }
    }
}
