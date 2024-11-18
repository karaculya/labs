package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CalculatorController {
    @FXML
    private Button button;
    @FXML
    private Label errorLbl, displayLbl;

    private String currentNumber = "0";
    private String firstNumber = "";
    private String operator;
    private double res = 0;

//    todo onNumberKeyBoard,onSymbolKeyBoard

    @FXML
    public void onNumberKeyBoard(KeyEvent event) {
        String input = event.getCharacter();
        if (input.matches("[0-9,]")) {
            setCurrentNumber(Integer.parseInt(input));
        }
    }

    @FXML
    public void onSymbolKeyBoard(KeyEvent event) {
        operator = event.getCode().getName();
        setOperator();
    }

    @FXML
    public void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Button) event.getSource()).getId().replace("btn", ""));
        setCurrentNumber(value);
    }

    @FXML
    public void onSymbolClicked(MouseEvent event) {
        operator = ((Button) event.getSource()).getId().replace("btn", "");
        setOperator();
    }

    @FXML
    public void onClearBtnClicked(MouseEvent event) {
        currentNumber = "0";
        firstNumber = "";
        operator = "";
        res = 0;
        displayLbl.setText(currentNumber);
        errorLbl.setText("");
    }

    @FXML
    public void onEqualsBtnClicked(MouseEvent event) {
        currentNumber = String.valueOf(res);
        displayLbl.setText(currentNumber);
    }

    @FXML
    public void onCommaBtnClicked(MouseEvent event) {
        if (displayLbl.getText().contains(".")) {
            errorLbl.setText("Comma must be one");
        } else {
            currentNumber += ".";
            displayLbl.setText(currentNumber);
        }
    }

    private void setCurrentNumber(int value) {
        currentNumber = displayLbl.getText().equals("0") ?
                String.valueOf(value)
                : displayLbl.getText() + value;

        displayLbl.setText(currentNumber);

        if (!firstNumber.isEmpty()) {
            calculate();
        }
    }

    private void setOperator() {
        operator = CalculatorUtils.getOperatorSymbol(operator);

        if (operator.equals("√")) {
            calculate();
        } else {
            firstNumber = currentNumber;
            currentNumber = "0";
            displayLbl.setText(currentNumber);
        }
    }

    public void calculate() {
        StringBuilder builder;
        double currentNumberDouble = Double.parseDouble(currentNumber);
        if (operator.equals("√")) {
            builder = new StringBuilder();
            if (res != 0) {
                try {
                    this.res = CalculatorUtils.calculate(operator, res, res, builder);
                } catch (RuntimeException e) {
                    errorLbl.setText(e.getMessage());
                }
            } else {
                try {
                    this.res = CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
                } catch (RuntimeException e) {
                    errorLbl.setText(e.getMessage());
                }
            }
        } else if (res != 0) {
            String s = String.valueOf(res);
            displayLbl.setText(s);
            builder = new StringBuilder(s);
            try {
                this.res = CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
            } catch (RuntimeException e) {
                errorLbl.setText(e.getMessage());
            }
        } else {
            double firstNumberDouble = Double.parseDouble(firstNumber);
            builder = new StringBuilder(String.valueOf(firstNumberDouble));
            try {
                this.res = CalculatorUtils.calculate(operator, firstNumberDouble, currentNumberDouble, builder);
            } catch (RuntimeException e) {
                errorLbl.setText(e.getMessage());
            }
        }
        displayLbl.setText(String.valueOf(builder));
    }
}