public class InstructionQueue {
    String[][] instructions;
    String[][] lastInstructions;

    public InstructionQueue() {
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

    public void clear() {
        lastInstructions = instructions;
        instructions = new String[0][];
    }

    public String[][] compress() {
        int offset = 0;
        for (int i = 0; i < instructions.length && i < lastInstructions.length; i++) {
            if (instructions[i].equals(lastInstructions[i])) {
                offset++;
            } else {
                break;
            }
        }
        String[][] compressedInstructions = new String[instructions.length - offset][];
        for (int i = 0; i < compressedInstructions.length; i++) {
            compressedInstructions[i] = instructions[i + offset];
        }
        return compressedInstructions;
    }
}