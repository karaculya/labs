package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.util.Arrays;

/*
Задание 1
Написать класс Автомобиль. Он должен содержать:
*/

public class Car implements Transport {
    //  поле типа String, хранящее марку автомобиля,
    private String mark;
    private Model[] models;

    //  Конструктор класса должен принимать в качестве параметров значение Марки автомобиля и размер массива Моделей.
    public Car(String mark, int quantity) {
        this.mark = mark;
        this.models = new Model[quantity];
    }

    //  метод для получения размера массива Моделей.
    public int getModelsSize() {
        return models.length;
    }

    //  метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (Arrays.stream(models).anyMatch(model -> model.modelName.equals(modelName))) {
            throw new DuplicateModelNameException();
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);
    }

    //  метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    public void removeModel(String modelName, double price) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(modelName))) {
            throw new NoSuchModelNameException();
        } else if (getPriceByModelName(modelName) != price) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            if (models.length - 1 == 0) models = new Model[0];
            Model[] newArray = new Model[models.length - 1];
            for (Model value : models) {
                for (int i = 0; i < newArray.length; i++) {
                    if (!(value.modelName.equals(modelName) && value.price == price)) {
                        newArray[i] = value;
                    }
                }
            }

            System.arraycopy(newArray, 0, models, 0, newArray.length);
            models = Arrays.copyOf(models, newArray.length);
        }
    }

    //  метод, возвращающий массив названий всех моделей,
    public String[] getAllModelNames() {
        String[] allModelNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            allModelNames[i] = models[i].modelName;
        }
        return allModelNames;
    }

    //  метод, возвращающий массив значений цен моделей,
    public double[] getAllModelPrices() {
        double[] array = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            array[i] = models[i].price;
        }
        return array;
    }

    //  метод для модификации значения цены модели по её названию,
    public void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(name))) {
            throw new NoSuchModelNameException();
        }

        Arrays.stream(models)
                .filter(model -> model.modelName.equals(name))
                .findFirst()
                .ifPresent(model -> model.price = newPrice);
    }

    //  метод для получения значения цены модели по её названию,
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if (Arrays.stream(models).noneMatch(model -> model.modelName.equals(name))) {
            throw new NoSuchModelNameException();
        }
        return Arrays.stream(models)
                .filter(model -> model.modelName.equals(name))
                .findFirst()
                .map(value -> value.price).orElse(0.0);
    }

    //  метод для получения марки автомобиля,
    public String getMark() {
        return mark;
    }

    //  метод для модификации марки автомобиля,
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

        //  метод для модификации значения названия модели,
        void setModelName(String modelName) throws DuplicateModelNameException {
            if (Arrays.stream(models).anyMatch(model -> model.modelName.equals(modelName))) {
                throw new DuplicateModelNameException();
            }
            this.modelName = modelName;
        }
    }
}
