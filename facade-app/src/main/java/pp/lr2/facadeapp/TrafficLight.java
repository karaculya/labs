package pp.lr2.facadeapp;

import javafx.scene.paint.Color;

public class TrafficLight {
    private int state = 0;
    private long lastChangeTime = System.currentTimeMillis();
    private static final long INTERVAL = 3000;

    public void update() {
        if (System.currentTimeMillis() - lastChangeTime > INTERVAL) {
            state = (state + 1) % 3;
            lastChangeTime = System.currentTimeMillis();
        }
    }

    public Color getColor() {
        return switch (state) {
            case 0 -> Color.RED;
            case 1 -> Color.YELLOW;
            case 2 -> Color.GREEN;
            default -> Color.BLACK;
        };
    }

    public boolean isRed() {
        return state == 0;
    }
}
