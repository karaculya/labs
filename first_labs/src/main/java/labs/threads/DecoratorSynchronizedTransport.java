package main.java.labs.threads;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;
import main.java.labs.model.Transport;

public class DecoratorSynchronizedTransport implements Transport {
    private Transport transport;

    public DecoratorSynchronizedTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized int getSize() {
        return transport.getSize();
    }

    @Override
    public synchronized void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        transport.addNewModel(modelName, price);
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        transport.removeModel(modelName);
    }

    @Override
    public synchronized String[] getModels() {
        return transport.getModels();
    }

    @Override
    public synchronized double[] getPrices() {
        return transport.getPrices();
    }

    @Override
    public synchronized void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        transport.setPriceByModelName(name, newPrice);
    }

    @Override
    public synchronized double getPriceByModelName(String name) throws NoSuchModelNameException {
        return transport.getPriceByModelName(name);
    }

    @Override
    public synchronized void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(oldName, newName);
    }

    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized void setMark(String mark) {
        transport.setMark(mark);
    }

    @Override
    public synchronized Transport clone() {
        return null;
    }
}
