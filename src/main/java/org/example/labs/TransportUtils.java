package org.example.labs;

public class TransportUtils {

    public static double avg(Transport transport) {
        double count = 0;
        for (double price : transport.getAllModelPrices()) {
            count += price;
        }
        return count / transport.getModelsSize();
    }

    public static void printAllModelNames(Transport transport) {
        for (String modelName : transport.getAllModelNames()) {
            System.out.println(modelName);
        }
    }

    public static void printAllModelPrices(Transport transport) {
        for (double modelPrice : transport.getAllModelPrices()) {
            System.out.println(modelPrice);
        }
    }
}
