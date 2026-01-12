package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.di.Command;
import ru.aston.hometask.di.CommandType;
import ru.aston.hometask.input.CommandParser;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    private final CommandParser parser = new CommandParser();

    @Test
    void testParseValidCommand() {
        Command command = parser.parseCommand("fill 10 random");

        assertNotNull(command);
        assertEquals(CommandType.FILL, command.type);
        assertArrayEquals(new String[]{"10", "random"}, command.params);
    }

    @Test
    void testInvalidCommand() {
        Command command = parser.parseCommand("unknown_command");
        assertNull(command);
    }
}
