package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CalculatorController {
    @FXML
    private Label errorLbl, displayLbl, resLbl;

    private String currentNumber = "0";
    private String firstNumber = "";
    private String operator;
    private double res = 0;

    @FXML
    public void onKeyPressed(KeyEvent event) {
        String input = event.getCode().getName();
        switch (input) {
            case "Clear", "Delete" -> clear();
            case "Enter", "Equals" -> equals();
            case "Comma" -> comma();
            case "Plus", "Minus", "Multiply", "Divide" -> setOperator(input);
            default -> {
                if (input.matches("[0-9,]")) {
                    setCurrentNumber(Integer.parseInt(input));
                } else {
                    System.out.println("KeyEvent don't matches with operations and numbers " + input);
                }
            }
        }
    }

    @FXML
    public void onClicked(MouseEvent event) {
        String input = ((Button) event.getSource()).getId().replace("btn", "");
        switch (input) {
            case "Clear" -> clear();
            case "Equals" -> equals();
            case "Comma" -> comma();
            case "Plus", "Minus", "Multiply", "Divide", "Pow", "Sqrt" -> setOperator(input);
            default -> {
                if (input.matches("[0-9,]")) {
                    setCurrentNumber(Integer.parseInt(input));
                }
            }
        }
    }

    private void setCurrentNumber(int value) {
        if (displayLbl.getText().equals("0")) {
            currentNumber = String.valueOf(value);
        } else if (currentNumber.contains(".")) {
            currentNumber = String.valueOf(Double.parseDouble(displayLbl.getText()) * 10 + value);
        } else {
            currentNumber += String.valueOf(value);
        }

        displayLbl.setText(currentNumber);

        if (!firstNumber.isEmpty()) {
            calculate();
        }
    }

    private void setOperator(String operator) {
        this.operator = CalculatorUtils.getOperatorSymbol(operator);

        if (this.operator.equals("√")) {
            calculate();
        } else {
            firstNumber = currentNumber;
            currentNumber = "0";
            updateDisplay();
        }
        resLbl.setText("");
    }

    private void clear() {
        currentNumber = "0";
        firstNumber = "";
        operator = "";
        res = 0;
        updateDisplay();
    }

    private void equals() {
        currentNumber = CalculatorUtils.numToString(res);
        updateDisplay();
    }

    private void comma() {
        if (displayLbl.getText().contains(".")) {
            errorLbl.setText("Comma must be one");
        } else {
            currentNumber += ".";
            displayLbl.setText(currentNumber);
        }
    }

    private void calculate() {
        StringBuilder builder;
        double currentNumberDouble = Double.parseDouble(currentNumber);
        try {
            if (operator.equals("√")) {
                builder = new StringBuilder();
                res = res != 0 ?
                        CalculatorUtils.calculate(operator, res, res, builder)
                        : CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
                resLbl.setText(CalculatorUtils.numToString(res));
            } else if (res != 0) {
                String s = CalculatorUtils.numToString(res);
                displayLbl.setText(s);
                builder = new StringBuilder(s);
                res = CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
            } else {
                double firstNumberDouble = Double.parseDouble(firstNumber);
                builder = new StringBuilder(CalculatorUtils.numToString(firstNumberDouble));
                res = CalculatorUtils.calculate(operator, firstNumberDouble, currentNumberDouble, builder);
            }

            displayLbl.setText(String.valueOf(builder));
            resLbl.setText(CalculatorUtils.numToString(res));
            System.out.println(res);
        } catch (RuntimeException e) {
            errorLbl.setText(e.getMessage());
        }
    }

    private void updateDisplay() {
        displayLbl.setText(currentNumber);
        resLbl.setText("");
        errorLbl.setText("");
    }
}