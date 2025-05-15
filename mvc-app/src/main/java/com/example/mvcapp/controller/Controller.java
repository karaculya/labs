package com.example.mvcapp.controller;

import com.example.mvcapp.model.Model;
import com.example.mvcapp.model.Point;
import com.example.mvcapp.view.CustomDoubleTableCell;
import com.example.mvcapp.view.View;
import javafx.scene.control.TableColumn;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        setupEventHandlers();
        setupTableEditing();
        updateView();
    }

    private void setupEventHandlers() {
        view.getAddButton().setOnAction(event -> {
            try {
                double x = Double.parseDouble(view.getXInput().getText());
                model.addPoint(x);
                updateView();
                view.getXInput().clear();
            } catch (NumberFormatException e) {
                view.getXInput().setText("Ошибка: введите число");
            }
        });

        view.getRemoveButton().setOnAction(event -> {
            try {
                double x = Double.parseDouble(view.getXInput().getText());
                model.removePoint(x);
                updateView();
                view.getXInput().clear();
            } catch (NumberFormatException e) {
                view.getXInput().setText("Ошибка: введите число");
            }
        });
    }

    // todo fix NumberFormatException
    private void setupTableEditing() {
        TableColumn<Point, Double> xColumn = (TableColumn<Point, Double>) view.getTable().getColumns().get(0);

        xColumn.setCellFactory(column -> new CustomDoubleTableCell<>() {
            @Override
            public void commitEdit(Double newValue) {
                if (newValue == null) return;

                Point point = getTableView().getItems().get(getIndex());
                double oldX = point.getX();

                if (newValue != oldX) {
                    model.updatePoint(oldX, newValue);
                    super.commitEdit(newValue);
                    updateView();
                }
            }
        });
    }

    private void updateView() {
        view.updateChartAndTable(model.getPoints());
    }
}