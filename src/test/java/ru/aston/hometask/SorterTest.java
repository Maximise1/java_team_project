package ru.aston.hometask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.sort.Sorter;
import ru.aston.hometask.util.CustomArray;

class SorterTest {

    private final Sorter sorter = new Sorter();

    private CustomArray<Bus> createTestBuses() {
        CustomArray<Bus> buses = new CustomArray<>();
        buses.add(new Bus("A001", "Ford", 15));
        buses.add(new Bus("A002", "Honda", 4));
        buses.add(new Bus("A003", "Mercedes", 10));
        buses.add(new Bus("A004", "UAZ", 3));
        return buses;
    }

    @Test
    void when_sortByMileage_then_correctOrder() {
        CustomArray<Bus> buses = createTestBuses();

        CustomArray<Bus> newBuses = sorter.sort(
                buses,
                false,
                Comparator.comparing(Bus::getMileage),
                null
        );

        assertEquals(3, newBuses.get(0).getMileage());
        assertEquals(4, newBuses.get(1).getMileage());
        assertEquals(10, newBuses.get(2).getMileage());
        assertEquals(15, newBuses.get(3).getMileage());
    }

    @Test
    void sort_sortByMileageInEvenMode_then_correctOrder() {
        CustomArray<Bus> buses = createTestBuses();

        CustomArray<Bus> newBuses = sorter.sort(
                buses,
                true,
                Comparator.comparing(Bus::getMileage),
                bus -> bus.getMileage() % 2 == 0
        );

        assertEquals(15, newBuses.get(0).getMileage());
        assertEquals(4, newBuses.get(1).getMileage());
        assertEquals(10, newBuses.get(2).getMileage());
        assertEquals(3, newBuses.get(3).getMileage());

        assertTrue(newBuses.get(1).getMileage() <= newBuses.get(2).getMileage());
    }
}
