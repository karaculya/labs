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
    private String operator = "";
    private double res = 0;
    private String error = "";

    @FXML
    public void onKeyPressed(KeyEvent event) {
        if (!error.isEmpty()) clear();
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
        if (!error.isEmpty()) clear();
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
        currentNumber = displayLbl.getText().equals("0") ?
                String.valueOf(value)
                : currentNumber + value;

        displayLbl.setText(currentNumber);
    }

    private void setOperator(String op) {
        String s = CalculatorUtils.getOperatorSymbol(op);

        if (s.equals("√")) {
            calculate();
            operator = s;
            calculate();
            operator = "";
        } else {
            if (firstNumber.isEmpty()) {
                firstNumber = currentNumber;
                displayLbl.setText("0");
            } else {
                calculate();
            }
            operator = s;
            System.out.println(operator);
        }
    }

    private void clear() {
        currentNumber = "";
        firstNumber = "";
        operator = "";
        res = 0;
        error = "";
        displayLbl.setText("0");
    }

    private void equals() {
        calculate();
        if (!error.isEmpty()) displayLbl.setText(error);
    }

    private void comma() {
        if (!displayLbl.getText().contains(".")) {
            currentNumber += ".";
            displayLbl.setText(currentNumber);
        }
    }

    private void calculate() {
        double currentNumberDouble = currentNumber.isEmpty() ? 0 : Double.parseDouble(currentNumber);
        try {
            if (operator.equals("√")) {
                res = res != 0 ?
                        CalculatorUtils.calculate(operator, res, res)
                        : CalculatorUtils.calculate(operator, res, currentNumberDouble);
            } else {
                res = res != 0 ?
                        CalculatorUtils.calculate(operator, res, currentNumberDouble)
                        : CalculatorUtils.calculate(operator, Double.parseDouble(firstNumber), currentNumberDouble);
            }
            firstNumber = CalculatorUtils.numToString(res);
            displayLbl.setText(firstNumber);
            currentNumber = "";
        } catch (RuntimeException e) {
            error = e.getMessage();
            displayLbl.setText(e.getMessage());
        }
    }
}