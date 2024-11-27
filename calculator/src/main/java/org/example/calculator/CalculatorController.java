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
//        currentNumber = (currentNumber.equals("0") || displayLbl.getText().equals("0")) ?
        currentNumber = displayLbl.getText().equals("0") ?
                String.valueOf(value)
                : currentNumber + value;

        System.out.println("IN METHOD setCurrentNumber(int value) currentNumber = " + currentNumber);

        displayLbl.setText(currentNumber);
    }

    private void setOperator(String operator) {
        String s = CalculatorUtils.getOperatorSymbol(operator);

        System.out.print("IN METHOD setOperator(String operator) ");

        if (!this.operator.isEmpty()) {
            calculate();
        }
        if (s.equals("√")) {
            calculate();
            this.operator = s;
            calculate();
        } else {
            if (firstNumber.isEmpty()) {
                firstNumber = currentNumber;
                System.out.println("firstNumber = " + firstNumber);
                displayLbl.setText("0");
            }
            this.operator = s;
        }
        System.out.println("this.operator = " + this.operator);
    }

    private void clear() {
        currentNumber = "0";
        firstNumber = "";
        operator = "";
        res = 0;
        error = "";
        displayLbl.setText(currentNumber);
    }

    private void equals() {
        calculate();
//        if (error.isEmpty()) displayLbl.setText(CalculatorUtils.numToString(res));
//        else displayLbl.setText(error);
        if (!error.isEmpty()) displayLbl.setText(error);
    }

    private void comma() {
        if (!displayLbl.getText().contains(".")) {
            currentNumber += ".";
            displayLbl.setText(currentNumber);
        }
    }

    private void calculate() {
        System.out.println("currentNumber = " + currentNumber + " firstNumber " + firstNumber);
        StringBuilder builder;
        double currentNumberDouble = Double.parseDouble(currentNumber);
        try {
            if (operator.equals("√")) {
                builder = new StringBuilder();
                res = res != 0 ?
                        CalculatorUtils.calculate(operator, res, res, builder) : CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
//            } else if (res != 0) {
//                String s = CalculatorUtils.numToString(res);
//                displayLbl.setText(s);
//                builder = new StringBuilder(s);
//                res = CalculatorUtils.calculate(operator, res, currentNumberDouble, builder);
            } else {
                builder = res != 0 ?
                        new StringBuilder(CalculatorUtils.numToString(res))
                        : new StringBuilder(CalculatorUtils.numToString(Double.parseDouble(firstNumber)));

                res = res != 0 ?
                        CalculatorUtils.calculate(operator, res, currentNumberDouble, builder)
                        : CalculatorUtils.calculate(operator, Double.parseDouble(firstNumber), currentNumberDouble, builder);

//                double firstNumberDouble = Double.parseDouble(firstNumber);
//                builder = new StringBuilder(CalculatorUtils.numToString(firstNumberDouble));
//                res = CalculatorUtils.calculate(operator, firstNumberDouble, currentNumberDouble, builder);
            }
            System.out.print("IN METHOD calculate()");
            System.out.println(builder + " = " + res);
            displayLbl.setText(CalculatorUtils.numToString(res));
            firstNumber = "";
        } catch (RuntimeException e) {
            error = e.getMessage();
            displayLbl.setText(e.getMessage());
        }
    }
}