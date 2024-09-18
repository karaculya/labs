package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

class CarTest {

    @Test
    void getModelsSize() {
        Transport car = new Car("mark", 0);

        assertEquals(0, car.getModelsSize());
    }

    @Test
    void addNewModel() throws DuplicateModelNameException, NoSuchModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);

        assertEquals(1, car.getModelsSize());
        assertTrue(car.getPriceByModelName("Toyota Camry") == 30);

        assertThrows(DuplicateModelNameException.class,
                () -> car.addNewModel("Toyota Camry", 40.0));
    }

    @Test
    void removeModel() throws DuplicateModelNameException, NoSuchModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);

        assertThrows(NoSuchModelNameException.class,
                () ->car.removeModel("Toyota Camry 35", 30.0));
        assertThrows(ModelPriceOutOfBoundsException.class,
                () -> car.removeModel("Toyota Camry", 20.0));

        car.removeModel("Toyota Camry", 30.0);
        assertEquals(0, car.getModelsSize());
    }

    @Test
    void getAllModelNames() throws DuplicateModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);
        car.addNewModel("Toyota Land Cruiser", 40.0);

        assertEquals(List.of("Toyota Camry", "Toyota Land Cruiser"), Arrays.stream(car.getAllModelNames()).toList());
    }

    @Test
    void getAllModelPrices() throws DuplicateModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);
        car.addNewModel("Toyota Land Cruiser", 40.0);

        double[] array = new double[] {30.0, 40.0};
        for (int i = 0; i < array.length; i++) {
            assertTrue(array[i] == car.getAllModelPrices()[i]);
        }
    }

    @Test
    void setPriceByModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);

        assertThrows(NoSuchModelNameException.class,
                () -> car.setPriceByModelName("Toyota Camry 35", 40.0));

        car.setPriceByModelName("Toyota Camry", 40.0);
        assertTrue(40.0 == car.getPriceByModelName("Toyota Camry"));
    }

    @Test
    void getPriceByModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        Transport car = new Car("mark", 0);

        car.addNewModel("Toyota Camry", 30.0);
        assertTrue(30.0 == car.getPriceByModelName("Toyota Camry"));
    }

    @Test
    void getMark() {
        Transport car = new Car("mark", 0);

        assertEquals("mark", car.getMark());
    }

    @Test
    void setMark() {
        Transport car = new Car("mark", 0);

        car.setMark("new mark");

        assertEquals("new mark", car.getMark());
    }
}