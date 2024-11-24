package org.example.calculator;

public class CalculatorUtils {
    static double calculate(String operator, double res, double num, StringBuilder builder) {
        switch (operator) {
            case "+" -> {
                builder.append("+").append(numToString(num));
                res += num;
            }
            case "-" -> {
                builder.append("-").append(numToString(num));
                res -= num;
            }
            case "*" -> {
                builder.append("*").append(numToString(num));
                res *= num;
            }
            case "/" -> {
                if (num == 0) {
                    throw new RuntimeException("Division by zero");
                } else {
                    builder.append("/").append(numToString(num));
                    res /= num;
                }
            }
            case "^" -> {
                builder.append("^").append(numToString(num));
                res = Math.pow(res, num);
            }
            case "√" -> {
                if (String.valueOf(num).contains("-")) {
                    throw new RuntimeException("Root of a negative number");
                } else {
                    builder.append("√").append(numToString(num));
                    res = Math.sqrt(num);
                }
            }
        }
        return res;
    }

    static String numToString(double num) {
        return String.valueOf(num).contains(".0") ?
                String.valueOf((long) num)
                : String.valueOf(num);
    }

    static String getOperatorSymbol(String operator) {
        return switch (operator) {
            case "Plus" -> "+";
            case "Minus" -> "-";
            case "Multiply" -> "*";
            case "Divide" -> "/";
            case "Pow" -> "^";
            case "Sqrt" -> "√";
            default -> operator;
        };
    }
}