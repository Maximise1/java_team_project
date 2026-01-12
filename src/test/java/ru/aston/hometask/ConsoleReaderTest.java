package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.input.ConsoleReader;
import ru.aston.hometask.util.CustomArray;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleReaderTest {

    @Test
    void when_readBusFromConsole_then_busesReturned() {
        String input = "А777АА77 Mercedes 1000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ConsoleReader reader = new ConsoleReader();
        CustomArray<Bus> buses = reader.read(1, null);

        assertEquals(1, buses.size());
        assertEquals("А777АА77", buses.get(0).getNumber());
        assertEquals("Mercedes", buses.get(0).getModel());
        assertEquals(1000, buses.get(0).getMileage());

        System.setIn(System.in);
    }
}
