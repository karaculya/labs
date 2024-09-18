package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;

interface Transport {
    //  метод для получения размера массива Моделей.
    int getModelsSize();
    //  метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    void addNewModel(String modelName, double price) throws DuplicateModelNameException;
    //  метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    void removeModel(String modelName, double price) throws NoSuchModelNameException;
    //  метод, возвращающий массив названий всех моделей,
    String[] getAllModelNames();
    //  метод, возвращающий массив значений цен моделей,
    double[] getAllModelPrices();
    //  метод для модификации значения цены модели по её названию,
    void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException;
    //  метод для получения значения цены модели по её названию,
    double getPriceByModelName(String name) throws NoSuchModelNameException;
    //  метод для получения марки автомобиля,
    String getMark();
    //  метод для модификации марки автомобиля,
    void setMark(String mark);
}
