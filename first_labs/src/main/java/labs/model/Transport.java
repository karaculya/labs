package main.java.labs.model;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;
import main.java.labs.patterns.behavioral.Visitor;

import java.io.Serializable;

public interface Transport extends Serializable {
    //  1. метод для получения размера массива Моделей.
    int getSize();
    //  2. метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    void addNewModel(String modelName, double price) throws DuplicateModelNameException;
    //  3. метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    void removeModel(String modelName) throws NoSuchModelNameException;
    //  4. метод, возвращающий массив названий всех моделей,
    String[] getModels();
    //  5. метод, возвращающий массив значений цен моделей,
    double[] getPrices();
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
    void accept(Visitor visitor);
}