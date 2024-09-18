package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.ModelPriceOutOfBoundsException;
import org.example.labs.exceptions.NoSuchModelNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MotorbikeTest {
    private Motorbike motorbike;

    @BeforeEach
    void setUp() {
        motorbike = new Motorbike("mark", 0);
    }

    @Test
    void getModelsSize() {
        assertEquals(0, motorbike.getModelsSize());
    }

    @Test
    void addNewModel() throws DuplicateModelNameException, NoSuchModelNameException {
        motorbike.addNewModel("model1", 50.0);

        assertEquals(1, motorbike.getModelsSize());
        assertEquals(50.0, motorbike.getPriceByModelName("model1"));
        assertThrows(DuplicateModelNameException.class,
                () -> motorbike.addNewModel("model1", 60.0));
    }

    @Test
    void removeModel() throws NoSuchModelNameException, DuplicateModelNameException {
        motorbike.addNewModel("model1", 50.0);

        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.removeModel("model", 50.0));
        assertThrows(ModelPriceOutOfBoundsException.class,
                () -> motorbike.removeModel("model1", 10.0));

        motorbike.removeModel("model1", 50.0);

        assertEquals(0, motorbike.getModelsSize());
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.getPriceByModelName("model1"));
    }

    @Test
    void getAllModelNames() throws DuplicateModelNameException {
        motorbike.addNewModel("model1", 10.0);
        motorbike.addNewModel("model2", 20.0);
        motorbike.addNewModel("model3", 30.0);

        String[] modelNames = {"model1", "model2", "model3"};
        assertEquals(modelNames.length, motorbike.getModelsSize());

        for (int i = 0; i < motorbike.getAllModelNames().length; i++) {
            for (int j = 0; j < modelNames.length; j++) {
                assertEquals(modelNames[i], motorbike.getAllModelNames()[i]);
            }
        }
    }

    @Test
    void getAllModelPrices() throws DuplicateModelNameException {
        motorbike.addNewModel("model1", 10.0);
        motorbike.addNewModel("model2", 20.0);
        motorbike.addNewModel("model3", 30.0);

        double[] modelPrices = {10.0, 20.0, 30.0};
        assertEquals(modelPrices.length, motorbike.getModelsSize());

        for (int i = 0; i < motorbike.getAllModelPrices().length; i++) {
            for (int j = 0; j < modelPrices.length; j++) {
                assertEquals(modelPrices[i], motorbike.getAllModelPrices()[i]);
            }
        }
    }

    @Test
    void setPriceByModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        motorbike.addNewModel("model", 30.0);

        assertEquals(30.0, motorbike.getPriceByModelName("model"));
        assertThrows(NoSuchModelNameException.class,
                () -> motorbike.getPriceByModelName("m"));

    }
}