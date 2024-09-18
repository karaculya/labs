package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.util.Arrays;
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
    private Model[] models;

    public Motorbike(String mark, int size) {
        this.mark = mark;
        this.models = new Model[size];
        if (size != 0) {
            this.head = this.models[0];
            if (size > 1) {
                this.head.prev = this.models[getModelsSize() - 1];
                this.head.next = this.models[1];
            }
        } else this.head = null;
        this.lastModified = new Date().getTime();
    }

    private void update() {
        if (this.head == null || getModelsSize() > 0) {
            this.head = this.models[0];
            if (getModelsSize() > 1) {
                this.head.prev = models[getModelsSize() - 1];
                this.head.next = models[1];
            }
        }
        this.lastModified = new Date().getTime();
    }

    @Override
    public int getModelsSize() {
        return this.models.length;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (Arrays.stream(models).anyMatch(model -> model.modelName.equals(modelName))) {
            throw new DuplicateModelNameException();
        } else {
            if (head == null) {
                models = new Model[1];
                models[getModelsSize() - 1] = new Model(modelName, models[0], price);
            } else {
                Model newModel = new Model(modelName, models[getModelsSize() - 1], price);
                models[getModelsSize() - 1].next = newModel;
                models = Arrays.copyOf(models, getModelsSize() + 1);
                models[getModelsSize() - 1] = newModel;
            }
            update();
        }
    }

    @Override
    public void removeModel(String modelName, double price) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(modelName))) {
            throw new NoSuchModelNameException();
        } else if (getPriceByModelName(modelName) != price) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            if (getModelsSize() <= 1) {
                models = new Model[0];
            } else {
                Model[] newModels = new Model[getModelsSize() - 1];
                for (Model model : models) {
                    for (int i = 0; i < newModels.length; i++) {
                        if (model.modelName.equals(modelName) && model.price == price) {
                            model.next.prev = model.prev;
                            model.prev.next = model.next;
                        } else newModels[i] = model;
                    }
                }
                models = new Model[newModels.length];
                System.arraycopy(newModels, 0, models, 0, newModels.length);
            }
            update();
        }
    }

    @Override
    public String[] getAllModelNames() {
        String[] allModels = new String[getModelsSize()];
        for (int i = 0; i < getModelsSize(); i++) {
            allModels[i] = models[i].modelName;
        }
        return allModels;
    }

    @Override
    public double[] getAllModelPrices() {
        double[] allPrices = new double[getModelsSize()];
        for (int i = 0; i < getModelsSize(); i++) {
            allPrices[i] = models[i].price;
        }
        return allPrices;
    }

    @Override
    public void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(name))) {
            throw new NoSuchModelNameException();
        } else {
            for (Model model : models) {
                if (model.modelName.equals(name)) {
                    model.price = newPrice;
                }
            }
            update();
        }
    }

    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(name))) {
            throw new NoSuchModelNameException();
        } else {
            for (Model model : models) {
                if (model.modelName.equals(name)) {
                    return model.price;
                }
            }
        }
        return 0;
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

        public Model(String modelName, Model prev, double price) {
            this.modelName = modelName;
            this.next = head;
            this.prev = prev;
            this.price = price;
        }
    }
}