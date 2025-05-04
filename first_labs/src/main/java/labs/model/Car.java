package main.java.labs.model;


import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.ModelPriceOutOfBoundsException;
import main.java.labs.exceptions.NoSuchModelNameException;

import java.io.*;
import java.util.Arrays;

public class Car implements Transport {
    //  поле типа String, хранящее марку автомобиля,
    private String mark;
    private Model[] models;

    //  Конструктор класса должен принимать в качестве параметров значение Марки автомобиля и размер массива Моделей.
    public Car(String mark, int modelsSize) {
        this.mark = mark;
        this.models = new Model[modelsSize];
        for (int i = 0; i < modelsSize; i++) {
            this.models[i] = new Model("name" + (i + 1), i + 1);
        }
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car cloned = (Car) super.clone();
        cloned.models = Arrays.copyOf(this.models, getSize());
        for (int i = 0; i < getSize(); i++) {
            cloned.models[i] = (Model) this.models[i].clone();
        }
        return cloned;
    }

    @Override
    public int getSize() {
        return this.models.length;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (price <= 0) throw new ModelPriceOutOfBoundsException();
        else {
            for (Model model : this.models) {
                if (model.modelName.equals(modelName)) throw new DuplicateModelNameException();
            }

            this.models = Arrays.copyOf(this.models, getSize() + 1);
            this.models[getSize() - 1] = new Model(modelName, price);
        }
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        if ((getSize() == 1 && !this.models[0].modelName.equals(modelName))
                || getSize() == 0)
            throw new NoSuchModelNameException();
        else if (getSize() == 1 && this.models[0].modelName.equals(modelName))
            this.models = new Model[0];
        else {
            boolean isContains = false;

            for (int i = 0; i < getSize(); i++) {
                if (models[i].modelName.equals(modelName)) {
                    isContains = true;
                    System.arraycopy(models, i + 1, models, i, getSize() - i - 1);
                    models = Arrays.copyOf(models, getSize() - 1);
                    break;
                }
            }

            if (!isContains) throw new NoSuchModelNameException();
        }
    }

    @Override
    public void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice > 0) {
            if (getSize() >= 1) {
                boolean isContains = false;

                for (Model model : models) {
                    if (model.modelName.equals(name)) {
                        model.price = newPrice;
                        isContains = true;
                        break;
                    }
                }

                if (!isContains) throw new NoSuchModelNameException();
            } else
                throw new NoSuchModelNameException();
        } else
            throw new ModelPriceOutOfBoundsException();
    }

    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if (getSize() >= 1) {
            int countIteration = 0;
            for (Model model : models) {
                if (model.modelName.equals(name)) return model.price;
                else countIteration++;
            }
            if (countIteration == getSize()) throw new NoSuchModelNameException();
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (oldName.equals(newName)) throw new DuplicateModelNameException();
        if (getSize() >= 1) {
            boolean isContains = false;
            int index = 0;

            for (int i = 0; i < getSize(); i++) {
                if (models[i].modelName.equals(newName)) throw new DuplicateModelNameException();
                else if (models[i].modelName.equals(oldName)) {
                    index = i;
                    isContains = true;
                }
            }

            if (!isContains) throw new NoSuchModelNameException();

            models[index].modelName = newName;
        } else throw new NoSuchModelNameException();
    }

    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public double[] getPrices() {
        double[] array = new double[getSize()];
        for (int i = 0; i < getSize(); i++)
            array[i] = models[i].price;
        return array;
    }

    @Override
    public String[] getModels() {
        String[] allModelNames = new String[getSize()];
        for (int i = 0; i < getSize(); i++)
            allModelNames[i] = models[i].modelName;
        return allModelNames;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mark='" + mark + '\'' +
                ", models=" + Arrays.toString(models) +
                '}';
    }

    //  внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор (класс Автомобиль хранит массив Моделей),
    private class Model implements Serializable, Cloneable {

        private String modelName;
        private double price;

        Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
