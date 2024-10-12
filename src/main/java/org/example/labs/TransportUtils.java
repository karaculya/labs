package org.example.labs;

public class TransportUtils {

    public static double avg(Transport transport) {
        double count = 0;
        double[] allModelPrices = transport.getAllModelPrices();
        for (double price : allModelPrices) {
            count += price;
        }
        return count / transport.getModelsSize();
    }

    public static void printAllModelNames(Transport transport) {
        String[] allModelNames = transport.getAllModelNames();
        for (String modelName : allModelNames) {
            System.out.println(modelName);
        }
    }

    public static void printAllModelPrices(Transport transport) {
        double[] allModelPrices = transport.getAllModelPrices();
        for (double modelPrice : allModelPrices) {
            System.out.println(modelPrice);
        }
    }
}