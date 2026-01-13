package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusBuilderTest {

    Bus bus = Bus.builder().build();

    @Test
    void when_busNumberIsString_then_returnTrue() {
        assertTrue(bus.getNumber() != null && bus.getNumber() instanceof String);
    }

    @Test
    void when_busModelIsString_then_returnTrue() {
        assertTrue(bus.getModel() != null && bus.getModel() instanceof String);
    }

    @Test
    void when_busMileageIsString_then_returnTrue() {
        assertEquals((bus.getMileage() * -1), -bus.getMileage());
    }
}
