package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.util.DataValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataValidatorTest {

    @Test
    void when_dataIsValid_then_returnTrue() {
        assertTrue(DataValidator.isValid("А777АА77", "Mercedes", "1000"));
        assertTrue(DataValidator.isValid("X123TO199", "Ford", "0"));
    }

    @Test
    void when_busNumberIsInvalid_then_returnFalse() {
        assertFalse(DataValidator.isValid("Q155BV124", "Ford", "100"));
        assertFalse(DataValidator.isValid("TTTT1444", "Ford", "100"));
    }

    @Test
    void when_modelIsInvalidOrNull_then_returnFalse() {
        assertFalse(DataValidator.isValid("X123TO199", "1232176_Model", "-100"));
        assertFalse(DataValidator.isValid("X123TO199", null, "-100"));
    }

    @Test
    void when_mileageIsInvalid_then_returnFalse() {
        assertFalse(DataValidator.isValid("X123TO199", "Ford", "-100"));
        assertFalse(DataValidator.isValid("X123TO199", "Ford", "not_number"));
    }
}
