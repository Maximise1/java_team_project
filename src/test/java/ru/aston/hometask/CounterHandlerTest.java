package ru.aston.hometask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.aston.hometask.count.CounterHandler;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.util.CustomArray;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CounterHandlerTest {

    private CounterHandler handler;
    private TestBusRepository repository;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        repository = new TestBusRepository();
        handler = new CounterHandler(repository);
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteCommand_CountsThreeMatchingBuses() {
        repository.addBus(new Bus("A123", "ModelX", 50000));
        repository.addBus(new Bus("B456", "ModelY", 60000));
        repository.addBus(new Bus("A123", "ModelX", 50000));
        repository.addBus(new Bus("A123", "ModelX", 50000));

        String[] args = {"A123", "ModelX", "50000"};

        handler.executeCommand(args);

        String output = outputStream.toString().trim();
        assertEquals("3", output);
    }

    @Test
    void testExecuteCommand_WithEmptyRepository() {
        String[] args = {"A123", "ModelX", "50000"};

        handler.executeCommand(args);

        assertEquals("0", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_WithNonMatchingBuses() {
        repository.addBus(new Bus("B100", "ModelA", 10000));
        repository.addBus(new Bus("C200", "ModelB", 20000));
        repository.addBus(new Bus("D300", "ModelC", 30000));

        String[] args = {"Z999", "ModelZ", "99999"};

        handler.executeCommand(args);

        assertEquals("0", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_WithSingleMatchingBus() {
        repository.addBus(new Bus("E100", "Express", 40000));
        repository.addBus(new Bus("F200", "Local", 30000));
        repository.addBus(new Bus("G300", "Regional", 35000));

        String[] args = {"E100", "Express", "40000"};

        handler.executeCommand(args);

        assertEquals("1", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_WithAllMatchingBuses() {
        for (int i = 0; i < 10; i++) {
            repository.addBus(new Bus("G555", "MainRoute", 80000));
        }

        String[] args = {"G555", "MainRoute", "80000"};

        handler.executeCommand(args);

        assertEquals("10", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_DifferentMileageSameNumberAndModel() {
        repository.addBus(new Bus("H123", "ModelX", 50000));
        repository.addBus(new Bus("H123", "ModelX", 60000));
        repository.addBus(new Bus("H123", "ModelX", 50000));
        repository.addBus(new Bus("H123", "ModelX", 70000));

        String[] args = {"H123", "ModelX", "50000"};

        handler.executeCommand(args);

        assertEquals("2", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_DifferentNumberSameModelAndMileage() {
        repository.addBus(new Bus("X100", "ModelZ", 25000));
        repository.addBus(new Bus("X200", "ModelZ", 25000));
        repository.addBus(new Bus("X100", "ModelZ", 25000));

        String[] args = {"X100", "ModelZ", "25000"};

        handler.executeCommand(args);

        assertEquals("2", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_WithLargeDataset() {
        Bus targetBus = new Bus("TARGET", "Special", 100000);

        for (int i = 0; i < 50; i++) {
            repository.addBus(new Bus("TARGET", "Special", 100000));
        }

        for (int i = 0; i < 100; i++) {
            repository.addBus(new Bus("OTHER" + i, "Model" + i, i * 1000));
        }

        String[] args = {"TARGET", "Special", "100000"};

        handler.executeCommand(args);

        assertEquals("50", outputStream.toString().trim());
    }

    @Test
    void testExecuteCommand_ParsesIntegerMileageCorrectly() {
        repository.addBus(new Bus("TEST", "Model", 999999));

        String[] args = {"TEST", "Model", "999999"};

        assertDoesNotThrow(() -> handler.executeCommand(args));
        assertEquals("1", outputStream.toString().trim());
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private static class TestBusRepository extends BusRepository {
        private CustomArray<Bus> buses = new CustomArray<>();

        public void addBus(Bus bus) {
            buses.add(bus);
        }

        @Override
        public CustomArray<Bus> getBuses() {
            return buses;
        }
    }
}
