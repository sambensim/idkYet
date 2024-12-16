import java.awt.Dimension;
import java.awt.Toolkit;

public class CanvasManager {
    Drawing drawing;
    Shapes shapes;

    public CanvasManager() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawing = new Drawing(screenSize.width, screenSize.height);
        shapes = new Shapes(drawing);
        shapes.fill(new Color(0));
    }
    
    public void draw() {
        // drawing.transpose(drawing.width / 2, drawing.height / 2);
        while (true) {
            update();
            drawing.update();
        }
    }

    void update() {
        InstructionQueue plan = new InstructionQueue();
        plan.circle(drawing.width / 2, drawing.height / 2, 100, Color.WHITE.getCode());
        try {
            drawing.setAll(ReadInstructions.class, plan.instructions);
        } catch (Exception e) {
            System.out.println(e);
        }
        // int rad = (int) Math.round(drawing.getTime() / 5);
        // Color color = Color.WHITE;
        // while (rad > drawing.width / 3 * 2) {
        //     rad -= 600;
        // }
        // while (rad > 0) {
        //     shapes.circle(0, 0, rad, color);
        //     rad -= 100;
        //     color = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        // }
        // drawing.update();
    }
}
