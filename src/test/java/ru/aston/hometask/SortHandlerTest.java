package ru.aston.hometask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.sort.SortHandler;

class SortHandlerTest {

    private BusRepository repository;
    private SortHandler handler;

    @BeforeEach
    void setUp() {
        repository = new BusRepository();
        repository.addBus(new Bus("A001", "Ford", 150));
        repository.addBus(new Bus("A002", "Honda", 51));
        repository.addBus(new Bus("A003", "Mercedes", 100));

        handler = new SortHandler(repository);
    }

    @Test
    void executeCommand_shouldSortByMileage() {
        handler.executeCommand(new String[]{"mileage"});

        assertEquals(51, repository.getBuses().get(0).getMileage());
        assertEquals(100, repository.getBuses().get(1).getMileage());
        assertEquals(150, repository.getBuses().get(2).getMileage());
    }

    @Test
    void executeCommand_shouldRejectInvalidField() {
        ByteArrayOutputStream out = ConsoleTestUtil.interceptOut();

        handler.executeCommand(new String[]{"color"});

        assertTrue(out.toString().contains("Поле color не найдено"));
    }

    @Test
    void executeCommand_shouldRejectEvenModeForModel() {
        ByteArrayOutputStream out = ConsoleTestUtil.interceptOut();

        handler.executeCommand(new String[]{"model", "even"});

        assertTrue(out.toString().contains("не может быть отсортировано в четном режиме"));
    }

    @Test
    void executeCommand_shouldSortEvenMileageOnly() {
        handler.executeCommand(new String[]{"mileage", "even"});

        assertEquals(100, repository.getBuses().get(0).getMileage());
        assertEquals(51, repository.getBuses().get(1).getMileage());
        assertEquals(150, repository.getBuses().get(2).getMileage());
    }

    @Test
    void executeCommand_shouldRejectTooManyArguments() {
        ByteArrayOutputStream out = ConsoleTestUtil.interceptOut();

        handler.executeCommand(new String[]{"mileage", "even", "extra"});

        assertTrue(out.toString().contains("Слишком много аргументов"));
    }
}