package com.example.mvcapp.view;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;

public class CustomDoubleTableCell<S> extends TableCell<S, Double> {
    private final TextField textField = new TextField();
    private final DoubleStringConverter converter = new DoubleStringConverter();

    public CustomDoubleTableCell() {
        textField.setOnAction(event -> commitEdit());
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) commitEdit();
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (isEmpty()) return;

        textField.setText(converter.toString(getItem()));
        setGraphic(textField);
        textField.selectAll();
        textField.requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(converter.toString(getItem()));
        setGraphic(null);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                textField.setText(converter.toString(getItem()));
                setGraphic(textField);
            } else {
                setText(converter.toString(getItem()));
                setGraphic(null);
            }
        }
    }

    private void commitEdit() {
        try {
            Double value = converter.fromString(textField.getText());
            commitEdit(value);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            cancelEdit();
        }
    }
}
