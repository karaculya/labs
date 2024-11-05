package org.example.labs.model;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.io.Serializable;

public interface Transport extends Serializable {
    //  1. метод для получения размера массива Моделей.
    int getModelsSize();
    //  2. метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    void addNewModel(String modelName, double price) throws DuplicateModelNameException;
    //  3. метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    void removeModel(String modelName) throws NoSuchModelNameException;
    //  4. метод, возвращающий массив названий всех моделей,
    String[] getAllModelNames();
    //  5. метод, возвращающий массив значений цен моделей,
    double[] getAllModelPrices();
    //  6. метод для модификации значения цены модели по её названию,
    void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException;
    //  7. метод для получения значения цены модели по её названию,
    double getPriceByModelName(String name) throws NoSuchModelNameException;
    //  8. с двумя параметрами типа string (old and new) 1 итерация;
// дубликат
    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    //  9. метод для получения марки автомобиля,
    String getMark();
    //  10. метод для модификации марки автомобиля,
    void setMark(String mark);
}
