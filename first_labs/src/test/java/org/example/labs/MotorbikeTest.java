package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;
import org.example.labs.model.Motorbike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MotorbikeTest {
    private Motorbike motorbike;

    @BeforeEach
    void setUp() throws DuplicateModelNameException {
        motorbike = new Motorbike("mark", 0);
        motorbike.addNewModel("model1", 50.0);
    }

    @Test
    void getModelsSize() {
        assertEquals(1, motorbike.getModelsSize());
    }

    @Test
    void addNewModel() throws NoSuchModelNameException {
        assertEquals(1, motorbike.getModelsSize());
        assertEquals(50.0, motorbike.getPriceByModelName("model1"));
        assertThrows(DuplicateModelNameException.class,
                () -> motorbike.addNewModel("model1", 60.0));
    }

    @Test
    void removeModel() throws NoSuchModelNameException {
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.removeModel("model"));

        motorbike.removeModel("model1");

        assertEquals(0, motorbike.getModelsSize());
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.getPriceByModelName("model1"));
    }

    @Test
    void getAllModelNames() throws DuplicateModelNameException {
        motorbike.addNewModel("model2", 20.0);
        motorbike.addNewModel("model3", 30.0);

        String[] exceptedModelNames = {"model1", "model2", "model3"};
        String[] actualModelNames = motorbike.getAllModelNames();
        assertEquals(exceptedModelNames.length, actualModelNames.length);
        for (int i = 0; i < exceptedModelNames.length; i++) {
            assertEquals(exceptedModelNames[i], actualModelNames[i]);
        }
    }

    @Test
    void getAllModelPrices() throws DuplicateModelNameException {
        motorbike.addNewModel("model2", 20.0);
        motorbike.addNewModel("model3", 30.0);

        double[] expectedModelPrices = {50.0, 20.0, 30.0};
        double[] actualModelPrices = motorbike.getAllModelPrices();
        assertEquals(expectedModelPrices.length, actualModelPrices.length);

        for (int i = 0; i < expectedModelPrices.length; i++) {
            assertEquals(expectedModelPrices[i], actualModelPrices[i]);
        }
    }

    @Test
    void setPriceByModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        motorbike.setPriceByModelName("model1", 40.0);

        assertEquals(40.0, motorbike.getPriceByModelName("model1"));
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.setPriceByModelName("m", 30.0));
    }

    @Test
    void getPriceByModelName() throws NoSuchModelNameException {
        assertEquals(50.0, motorbike.getPriceByModelName("model1"));

        motorbike.setPriceByModelName("model1", 30.0);
        assertEquals(30.0, motorbike.getPriceByModelName("model1"));
    }

    @Test
    void setModelName() throws NoSuchModelNameException, DuplicateModelNameException {
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.setModelName("asd", "dsa"));

        motorbike.setModelName("model1", "new model");
        assertThrows(DuplicateModelNameException.class,
                () -> motorbike.addNewModel("new model", 30.0));
    }
}