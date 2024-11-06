package test.java.labs;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;
import main.java.labs.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() throws DuplicateModelNameException {
        car = new Car("mark", 0);
        car.addNewModel("Toyota Camry", 30.0);
    }

    @Test
    void getModelsSize() {
        assertEquals(1, car.getModelsSize());
    }

    @Test
    void addNewModel() throws DuplicateModelNameException, NoSuchModelNameException {
        car.addNewModel("Camry 3 and 5", 30.0);

        assertEquals(2, car.getModelsSize());
        assertTrue(car.getPriceByModelName("Camry 3 and 5") == 30);

        assertThrows(DuplicateModelNameException.class,
                () -> car.addNewModel("Camry 3 and 5", 40.0));
    }

    @Test
    void removeModel() throws NoSuchModelNameException {
        assertThrows(NoSuchModelNameException.class,
                () -> car.removeModel("Toyota Camry 35"));

        car.removeModel("Toyota Camry");
        assertEquals(0, car.getModelsSize());
    }

    @Test
    void getAllModelNames() throws DuplicateModelNameException {
        car.addNewModel("Toyota Land Cruiser", 40.0);

        assertEquals(List.of("Toyota Camry", "Toyota Land Cruiser"), Arrays.stream(car.getAllModelNames()).toList());
    }

    @Test
    void getAllModelPrices() throws DuplicateModelNameException {
        car.addNewModel("Toyota Land Cruiser", 40.0);

        double[] expected = car.getAllModelPrices();
        double[] array = new double[]{30.0, 40.0};
        for (int i = 0; i < array.length; i++) {
            assertTrue(expected[i] == array[i]);
        }
    }

    @Test
    void setPriceByModelName() throws NoSuchModelNameException {
        assertThrows(NoSuchModelNameException.class,
                () -> car.setPriceByModelName("Toyota Camry 35", 40.0));

        car.setPriceByModelName("Toyota Camry", 40.0);
        assertTrue(40.0 == car.getPriceByModelName("Toyota Camry"));
    }

    @Test
    void getPriceByModelName() throws NoSuchModelNameException {
        assertTrue(30.0 == car.getPriceByModelName("Toyota Camry"));
    }

    @Test
    void setModelName() throws NoSuchModelNameException, DuplicateModelNameException {
        car.setModelName("Toyota Camry", "Camry 3 and 5");

        String[] models = car.getAllModelNames();
        boolean isContainsToyotaCamry = false;
        boolean isContainsCamry3And5 = false;
        for (String model : models) {
            if (model.equals("Toyota Camry")) isContainsToyotaCamry = true;
            if (model.equals("Camry 3 and 5")) isContainsCamry3And5 = true;
        }

        assertFalse(isContainsToyotaCamry);
        assertTrue(isContainsCamry3And5);
    }

    @Test
    void getMark() {
        assertEquals("mark", car.getMark());
    }

    @Test
    void setMark() {
        car.setMark("new mark");

        assertEquals("new mark", car.getMark());
    }
}