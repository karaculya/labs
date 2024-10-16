package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.util.Date;

/*
Написать класс Мотоцикл, реализующий функциональность,
сходную с классом из задания 1, основанный на двусвязном циклическом списке с головой.

Не забудьте менять значение этого поля при модификации объекта.
*/
public class Motorbike implements Transport {
    private int size;
    private Model head;
    private long lastModified;
    private String mark;

    public Motorbike(String mark, int size) {
        this.mark = mark;
        this.size = size;
        Model prev = null;
        for (int i = 0; i < size - 1; i++) {
            Model currentModel = new Model("", null, null, 0);
            if (i == 0) {
                this.head = new Model("", currentModel, currentModel, 0);
                currentModel.prev = this.head;
                currentModel.next = this.head;
            } else {
                currentModel.prev = prev;
                currentModel.next = this.head;
                this.head.prev.next = currentModel;
                this.head.prev = currentModel;
            }
            prev = currentModel;
        }
        this.lastModified = new Date().getTime();
    }

    @Override
    public int getModelsSize() {
        return this.size;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (price > 0) {
            if (getModelsSize() == 0) {
                this.head = new Model(modelName, null, null, price);
                this.head.prev = this.head;
                this.head.next = this.head;
            } else {
                Model currentModel = this.head;
                int emptyCount = 0;
                for (int i = 0; i < getModelsSize(); i++) {
                    if (currentModel.modelName.equals(modelName)) throw new DuplicateModelNameException();
                    else if (currentModel.modelName.isEmpty()) emptyCount++;
                    currentModel = currentModel.next;
                }

                if (emptyCount == getModelsSize()) {
                    this.head = new Model(modelName, this.head, this.head, price);
                    this.head.prev = this.head;
                    this.head.next = this.head;
                    this.size = 0;
                } else {
                    Model newModel = new Model(modelName, this.head, this.head.prev, price);
                    this.head.prev.next = newModel;
                    this.head.prev = newModel;
                }
            }
            this.size++;
        } else throw new ModelPriceOutOfBoundsException();
        this.lastModified = new Date().getTime();
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        if (getModelsSize() == 0) throw new NoSuchModelNameException();
        else {
            Model currentModel = this.head;
            int countIteration = 0;
            for (int i = 0; i < getModelsSize(); i++) {
                if (currentModel.modelName.equals(modelName)) {
                    currentModel.prev.next = currentModel.next;
                    currentModel.next.prev = currentModel.prev;
                    if (currentModel.equals(this.head)) {
                        this.head = this.head.next;
                    }
                    break;
                } else
                    countIteration++;

                currentModel = currentModel.next;
            }

            if (countIteration == getModelsSize()) throw new NoSuchModelNameException();
        }
        this.size--;
        this.lastModified = new Date().getTime();
    }

    @Override
    public String[] getAllModelNames() {
        String[] allModels = new String[getModelsSize()];
        Model currentModel = this.head;
        for (int i = 0; i < getModelsSize(); i++) {
            allModels[i] = currentModel.modelName;
            currentModel = currentModel.next;
        }
        return allModels;
    }

    @Override
    public double[] getAllModelPrices() {
        double[] allPrices = new double[getModelsSize()];
        Model currentModel = this.head;
        for (int i = 0; i < getModelsSize(); i++) {
            allPrices[i] = currentModel.price;
            currentModel = currentModel.next;
        }
        return allPrices;
    }

    @Override
    public void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice > 0) {
            if (this.head == null) throw new NoSuchModelNameException();

            Model currentModel = this.head;
            for (int i = 0; i < getModelsSize(); i++) {
                if (currentModel.modelName.equals(name)) {
                    currentModel.price = newPrice;
                    break;
                } else if (i == getModelsSize() - 1 && !currentModel.modelName.equals(name))
                    throw new NoSuchModelNameException();

                currentModel = currentModel.next;
            }
        } else throw new ModelPriceOutOfBoundsException();
        this.lastModified = new Date().getTime();
    }

    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if (getModelsSize() > 0) {
            Model currentModel = this.head;
            for (int i = 0; i < getModelsSize(); i++) {
                if (currentModel.modelName.equals(name)) return currentModel.price;
                else if (i == getModelsSize() - 1 && !currentModel.modelName.equals(name))
                    throw new NoSuchModelNameException();

                currentModel = currentModel.next;
            }
        } else throw new NoSuchModelNameException();
        return 0;
    }

    @Override
    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (oldName.equals(newName)) throw new DuplicateModelNameException();
        else if (this.head == null) throw new NoSuchModelNameException();
        else {
            Model currentModel = this.head;
            Model searchModel = null;
            for (int i = 0; i < getModelsSize(); i++) {
                if (currentModel.modelName.equals(newName))
                    throw new DuplicateModelNameException();
                else if (currentModel.modelName.equals(oldName))
                    searchModel = currentModel;
                currentModel = currentModel.next;
            }

            if (searchModel == null) throw new NoSuchModelNameException();

            searchModel.modelName = newName;
        }
        this.lastModified = new Date().getTime();
    }

    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    private class Model {
        String modelName;
        double price;
        Model prev;
        Model next;

        public Model(String modelName, Model next, Model prev, double price) {
            this.modelName = modelName;
            this.next = next;
            this.prev = prev;
            this.price = price;
        }
    }
}