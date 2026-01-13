package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.input.RandomReader;
import ru.aston.hometask.util.CustomArray;

import static org.junit.jupiter.api.Assertions.*;

public class RandomReaderTest {

    @Test
    void when_generateBuses_then_uniqueBusesReturned() {
        RandomReader reader = new RandomReader();
        int size = 50;
        CustomArray<Bus> buses = reader.read(size, null);

        assertEquals(size, buses.size());
        assertNotNull(buses.get(0).getNumber());
        assertNotEquals(buses.get(0).getNumber(), buses.get(1).getNumber());
    }
}
