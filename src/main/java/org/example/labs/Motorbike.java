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
        this.head = null;
        this.lastModified = new Date().getTime();
    }

    @Override
    public int getModelsSize() {
        return this.size;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (this.head == null) {
            this.head = new Model(modelName, null, price);
            this.size = 1;
        } else if (this.head.next == null) {
            if (this.head.modelName.equals(modelName)) throw new DuplicateModelNameException();
            else {
                this.head.next = new Model(modelName, this.head, price);
                this.head.prev = this.head.next;
                this.size++;
            }
        } else {
            if (this.head.modelName.equals(modelName)) throw new DuplicateModelNameException();
            Model currentModel = this.head;
            while (!currentModel.equals(this.head.prev)) {
                currentModel = currentModel.next;
                if (currentModel.modelName.equals(modelName)) throw new DuplicateModelNameException();
            }

            this.head.prev.next = new Model(modelName, this.head.prev, price);
            this.head.prev = this.head.prev.next;

            this.size++;
        }
        this.lastModified = new Date().getTime();
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        if (this.head == null) throw new NoSuchModelNameException();
        else if (this.head.next == null) {
            if (this.head.modelName.equals(modelName))
                this.head = null;
            else throw new NoSuchModelNameException();
        } else {
            Model currentModel = this.head;
            while (!currentModel.equals(this.head.prev)) {
                if (currentModel.next.equals(this.head.prev)) {
                    if (currentModel.next.modelName.equals(modelName))
                        currentModel = currentModel.next;
                    else throw new NoSuchModelNameException();
                }
                if (currentModel.modelName.equals(modelName)) {
                    currentModel.prev = currentModel.next;
                    currentModel.next.prev = currentModel.prev;
                    break;
                }
                currentModel = currentModel.next;
            }
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
        if (this.head == null) throw new NoSuchModelNameException();
        else if (this.head.next == null) {
            if (!this.head.modelName.equals(name)) throw new NoSuchModelNameException();
            else this.head.price = newPrice;
        } else {
            Model currentModel = this.head;
            while (currentModel.equals(this.head.prev)) {
                if (currentModel.next.equals(this.head.prev)) {
                    if (!currentModel.next.modelName.equals(name)) throw new NoSuchModelNameException();
                    else {
                        currentModel.next.price = newPrice;
                        break;
                    }
                }
                if (currentModel.modelName.equals(name)) {
                    if (currentModel.price == newPrice) throw new ModelPriceOutOfBoundsException();
                    currentModel.price = newPrice;
                    break;
                }
                currentModel = currentModel.next;
            }
        }
        this.lastModified = new Date().getTime();
    }

    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if (this.head == null) throw new NoSuchModelNameException();
        else if (this.head.next == null)
            if (!this.head.modelName.equals(name)) throw new NoSuchModelNameException();
            else return this.head.price;
        else {
            Model currentModel = this.head;
            while (currentModel.equals(this.head.prev)) {
                if (currentModel.next.equals(this.head.prev)) {
                    if (currentModel.next.modelName.equals(name)) return currentModel.next.price;
                    else throw new NoSuchModelNameException();
                }
                if (currentModel.modelName.equals(name)) return currentModel.price;
                currentModel = currentModel.next;
            }
        }
        return 0;
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException {
        if (this.head == null) throw new NoSuchModelNameException();
        else if (this.head.next == null) {
            if (this.head.modelName.equals(oldName))
                this.head.modelName = newName;
            else throw new NoSuchModelNameException();
        } else {
            Model currentModel = this.head;
            while (currentModel.equals(this.head.prev)) {
                if (currentModel.modelName.equals(oldName)) {
                    currentModel.modelName = newName;
                    break;
                }
                if (currentModel.next.equals(this.head.prev)) {
                    if (!currentModel.next.modelName.equals(oldName)) throw new NoSuchModelNameException();
                    else {
                        this.head.prev.modelName = newName;
                        break;
                    }
                }
                currentModel = currentModel.next;
            }
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

        public Model(String modelName, Model prev, double price) {
            this.modelName = modelName;
            this.price = price;
            this.prev = prev;
        }
    }
}