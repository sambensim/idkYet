import java.awt.Dimension;
import java.awt.Toolkit;

public class CanvasManager {
    Drawing drawing;
    Shapes shapes;
    InstructionQueue plan;
    int frame;

    public CanvasManager() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawing = new Drawing(screenSize.width, screenSize.height);
        shapes = new Shapes(drawing);
        shapes.fill(new Color(0));
        frame = 0;
    }
    
    public void draw() {
        drawing.transpose(drawing.width / 2, drawing.height / 2);
        plan = new InstructionQueue();
        while (true) {
            plan.clear();
            update();
            try {
                drawing.setAllThreaded(ReadInstructions.class, plan.compress());
            } catch (Exception e) {
                System.err.println("AHHHHH!");
                System.err.println(e);
                return ;
            }
            drawing.update();
            frame++;
        }
    }

    void update() {
        shapes.fill(Color.BLACK);
        double time = (drawing.cTime); //could be one frame behind
        plan.circle((int) Math.round(100 * Math.cos(time / 1000)), (int) Math.round(100 * Math.sin(time / 1000)), 100, Color.WHITE.getCode());
        try {
        } catch (Exception e) {
            System.out.println(e);
        }
        if (frame % 2 == 0) {
            plan.circle((int) -700, (int) -400, 20, Color.RED.getCode());
        }
        if (frame % 3 == 0) {
            plan.circle((int) -650, (int) -400, 20, Color.RED.getCode());
        }
        if (frame % 4 == 0) {
            plan.circle((int) -600, (int) -400, 20, Color.RED.getCode());
        }
        if (frame % 5 == 0) {
            plan.circle((int) -550, (int) -400, 20, Color.RED.getCode());
        }
    }
}
