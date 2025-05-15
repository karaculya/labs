package com.example.mvcapp.model;

import java.util.Map;
import java.util.TreeMap;

public class Model {
    private final Map<Double, Double> points = new TreeMap<>();
    public void addPoint(double x) {
        points.put(x, Math.sin(x));
    }

    public void removePoint(double x) {
        points.remove(x);
    }

    public void updatePoint(double oldX, double newX) {
        if (points.containsKey(oldX)) {
            points.remove(oldX);
            points.put(newX, Math.sin(newX));
        }
    }

    public Map<Double, Double> getPoints() {
        return points;
    }
}