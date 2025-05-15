package com.example.mvcapp.view;

import com.example.mvcapp.model.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Map;

public class View {
    private final Stage stage;
    private final LineChart<Number, Number> chart;
    private final XYChart.Series<Number, Number> series;
    private final TextField xInput;
    private final Button addButton;
    private final Button removeButton;
    private final TableView<Point> table;
    private final ObservableList<Point> tableData;

    public View(Stage stage) {
        this.stage = stage;
        this.tableData = FXCollections.observableArrayList();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        this.chart = new LineChart<>(xAxis, yAxis);
        this.series = new XYChart.Series<>();
        this.xInput = new TextField();
        this.addButton = new Button("Добавить точку");
        this.removeButton = new Button("Удалить точку");
        this.table = new TableView<>();

        initializeUI();
    }

    private void initializeUI() {
        chart.setTitle("y = sin(x)");
        series.setName("sin(x)");
        chart.getData().add(series);

        xInput.setPromptText("Введите x");
        table.setEditable(true);

        TableColumn<Point, Double> xColumn = new TableColumn<>("X");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        xColumn.setPrefWidth(150);

        TableColumn<Point, Double> yColumn = new TableColumn<>("Y");
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        yColumn.setPrefWidth(150);

        table.getColumns().addAll(xColumn, yColumn);
        table.setItems(tableData);

        table.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            xColumn.setPrefWidth(width / 2);
            yColumn.setPrefWidth(width / 2);
        });

        HBox topPanel = new HBox(15, xInput, addButton, removeButton);
        topPanel.setPadding(new Insets(15));

        HBox.setHgrow(xInput, Priority.ALWAYS);
        xInput.setMaxWidth(Double.MAX_VALUE);

        Region spacer = new Region();
        spacer.setPrefHeight(10);

        BorderPane root = new BorderPane();
        root.setTop(topPanel);
        root.setCenter(table);
        root.setBottom(chart);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("График синуса");
    }

    public void updateChartAndTable(Map<Double, Double> points) {
        series.getData().clear();
        tableData.clear();

        points.forEach((x, y) -> {
            series.getData().add(new XYChart.Data<>(x, y));
            tableData.add(new Point(x, y));
        });
    }

    public void show() {
        stage.show();
    }

    public TextField getXInput() {
        return xInput;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public TableView<Point> getTable() {
        return table;
    }
}