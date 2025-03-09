package pp.lr2.facadeapp;

public class Car {
    private double x = 50;
    private static final double SPEED = 2;

    public void update(boolean stop) {
        if (!stop) {
            x += SPEED;
            if (x > 360) {
                x = -40;
            }
        }
    }

    public double getX() {
        return x;
    }
}
