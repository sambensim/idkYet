class PixelMath extends PixelFunction {
    public PixelMath(PixelInfo pixel) {
        super(pixel);
    }

    public void run() {
        int x = pixel.x;
        int y = pixel.y;
        int color = (int) Math.round(pixel.time) % 256;
        pixel.context.setPixel(x, y, new Color(color));
    }
}

class ReadInstructions extends PixelFunction {
    String[][] instructions;

    public ReadInstructions(PixelInfo pixel, String[][] instructions) {
        super(pixel);
        this.instructions = instructions;
    }

    public void run() {
        for (int i = instructions.length - 1; i >= 0; i--) {
            if (execute(instructions[i])) {
                return;
            }
        }
    }

    private boolean execute(String[] instruction) {
        switch (instruction[0]) {
            case "c":
                int cx = Integer.parseInt(instruction[1]);
                int cy = Integer.parseInt(instruction[2]);
                int radius = Integer.parseInt(instruction[3]);
                int dx = pixel.x - cx;
                int dy = pixel.y - cy;
                if (dx * dx + dy * dy <= radius * radius) { //I think I understand...?
                    pixel.context.setPixel(pixel.x, pixel.y, new Color(Integer.parseInt(instruction[4])));
                    return true;
                }
                break;
        }
        return false;
    }
}