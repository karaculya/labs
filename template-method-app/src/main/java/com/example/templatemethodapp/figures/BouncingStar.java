package com.example.templatemethodapp.figures;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BouncingStar extends BouncingShape {
    private Polygon star;

    public BouncingStar(Pane pane) {
        super(pane);
        star = new Polygon();

        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 5 * i - Math.PI / 2;
            double radius = (i % 2 == 0) ? 20 : 10;
            double x = 20 + radius * Math.cos(angle);
            double y = 20 + radius * Math.sin(angle);
            star.getPoints().addAll(x, y);
        }

        star.setFill(Color.YELLOW);
        star.setLayoutX(pane.getWidth() - 40);
        star.setLayoutY(pane.getHeight() - 40);
        pane.getChildren().add(star);
    }

    @Override
    protected void move() {
        Bounds bounds = pane.getBoundsInLocal();
        Bounds starBounds = star.getBoundsInParent();

        if (starBounds.getMinX() <= bounds.getMinX() || starBounds.getMaxX() >= bounds.getMaxX()) {
            dx = -dx;
        }
        if (starBounds.getMinY() <= bounds.getMinY() || starBounds.getMaxY() >= bounds.getMaxY()) {
            dy = -dy;
        }

        star.setLayoutX(star.getLayoutX() + dx);
        star.setLayoutY(star.getLayoutY() + dy);
    }
}
