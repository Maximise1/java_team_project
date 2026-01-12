package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.input.InputHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {

    @Test
    void testExecuteCommandWithValidArgs() {
        BusRepository repository = new BusRepository();
        InputHandler handler = new InputHandler(repository);

        handler.executeCommand(new String[]{"100", "random"});
        assertEquals(100, repository.getBuses().size());
    }

    @Test
    void testExecuteCommandWithInvalidArgs() {
        BusRepository repository = new BusRepository();
        InputHandler handler = new InputHandler(repository);

        handler.executeCommand(new String[]{"100"});
        assertEquals(0, repository.getBuses().size());
    }
}
