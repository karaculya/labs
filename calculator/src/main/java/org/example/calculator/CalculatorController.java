package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CalculatorController {
    @FXML
    private Label displayLbl;

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
            case "P" -> setOperator("Plus");
            case "Minus" -> setOperator(input);
            case "M" -> setOperator("Multiply");
            case "D" -> setOperator("Divide");
            case "O" -> setOperator("Pow");
            case "S" -> setOperator("Sqrt");
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
            currentNumber += String.valueOf(value);
        } else {
            currentNumber = String.valueOf(Long.parseLong(displayLbl.getText()) * 10 + value);
        }

        displayLbl.setText(currentNumber);
    }

    private void setOperator(String operator) {
        this.operator = CalculatorUtils.getOperatorSymbol(operator);

        if (this.operator.equals("√")) {
            calculate();
        } else {
            firstNumber = currentNumber;
            currentNumber = "0";
            displayLbl.setText(currentNumber);
        }
    }

    private void clear() {
        currentNumber = "0";
        firstNumber = "";
        operator = "";
        res = 0;
        displayLbl.setText(currentNumber);
    }

    private void equals() {
        calculate();
        displayLbl.setText(CalculatorUtils.numToString(res));
    }

    private void comma() {
        if (displayLbl.getText().contains(".")) {
            displayLbl.setText("Comma must be one");
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
                    CalculatorUtils.calculate(operator, res, res, builder) : CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
                displayLbl.setText(CalculatorUtils.numToString(res));
            } else if (res != 0) {
                String s = CalculatorUtils.numToString(res);
                displayLbl.setText(s);
                builder = new StringBuilder(s);
                res = CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
                displayLbl.setText(CalculatorUtils.numToString(res));
            } else {
                double firstNumberDouble = Double.parseDouble(firstNumber);
                builder = new StringBuilder(CalculatorUtils.numToString(firstNumberDouble));
                res = CalculatorUtils.calculate(operator, firstNumberDouble, currentNumberDouble, builder);
            }
            System.out.println(builder + " = " + res);
        } catch (RuntimeException e) {
            displayLbl.setText(e.getMessage());
        }
    }
}