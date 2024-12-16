public class InstructionQueue {
    String[][] instructions;

    InstructionQueue() {
        instructions = new String[0][];
    }

    public void circle(int cx, int cy, int radius, int color) {
        queue(new String[] {"c", Integer.toString(cx), Integer.toString(cy), Integer.toString(radius), Integer.toString(color)});
    }

    public void queue(String[] value) {
        String[][] temp = instructions;
        instructions = new String[temp.length + 1][];
        for (int i = 0; i < temp.length; i++) {
            instructions[i] = temp[i];
        }
        instructions[temp.length] = value;
    }
}