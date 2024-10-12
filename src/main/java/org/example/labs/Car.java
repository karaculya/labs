package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Car implements Transport{
    private String mark;
    private Model[] models;

    //  Конструктор класса должен принимать в качестве параметров значение Марки автомобиля и размер массива Моделей.
    public Car(String mark, int modelsSize) {
        this.mark = mark;
        this.models = new Model[modelsSize];
        for (int i = 0; i < modelsSize; i++) {
            this.models[i] = null;
        }
    }

    @Override
    public int getModelsSize() {
        return this.models.length;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException();
        else if (getModelsSize() >= 1 && models[0] == null) {
            models = new Model[1];
            models[0] = new Model(modelName, price);
        } else {
            for (Model model : models) {
                if (model != null && model.modelName.equals(modelName)) throw new DuplicateModelNameException();
            }
            models = Arrays.copyOf(models, getModelsSize() + 1);
            models[getModelsSize() - 1] = new Model(modelName, price);
        }
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        if ((getModelsSize() == 1 && !this.models[0].modelName.equals(modelName))
                || getModelsSize() == 0)
            throw new NoSuchModelNameException();
        else if (getModelsSize() == 1 && this.models[0].modelName.equals(modelName)) {
            this.models = new Model[0];
        } else {
            Model[] newArray = new Model[getModelsSize() - 1];
            boolean isContains = false;

            for (int i = 0, j = 0; i < getModelsSize(); i++) {
                if (models[i].modelName.equals(modelName))
                    isContains = true;
                else if (j < newArray.length) {
                    newArray[j] = models[i];
                    j++;
                }
            }

            if (!isContains) throw new NoSuchModelNameException();

            System.arraycopy(newArray, 0, models, 0, newArray.length);
            models = Arrays.copyOf(models, newArray.length);
        }
    }

    @Override
    public String[] getAllModelNames() {
        String[] allModelNames = new String[getModelsSize()];
        for (int i = 0; i < getModelsSize(); i++) {
            allModelNames[i] = models[i].modelName;
        }
        return allModelNames;
    }

    @Override
    public double[] getAllModelPrices() {
        double[] array = new double[getModelsSize()];
        for (int i = 0; i < getModelsSize(); i++) {
            array[i] = models[i].price;
        }
        return array;
    }

    @Override
    public void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice > 0) {
            if (getModelsSize() >= 1) {
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
        if (getModelsSize() >= 1) {
            for (Model model : models) {
                if (model.modelName.equals(name)) return model.price;
            }
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException {
        if (getModelsSize() >= 1) {
            boolean isContains = false;

            for (Model model : models) {
                if (model.modelName.equals(oldName)) {
                    model.modelName = newName;
                    isContains = true;
                    break;
                }
            }

            if (!isContains) throw new NoSuchModelNameException();
        } else
            throw new NoSuchModelNameException();
    }

    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    //  внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор (класс Автомобиль хранит массив Моделей),
    private class Model {

        private String modelName;
        private double price;

        Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }
    }
}
