package ru.aston.hometask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.aston.hometask.di.Command;
import ru.aston.hometask.di.CommandType;
import ru.aston.hometask.input.CommandParser;

class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void when_correctValuesPassed_then_correctCommandsReturned() { // TODO: добавить тесты команды count
        Command sortCommand1 = parser.parseCommand("sort number even");
        Command sortCommand2 = parser.parseCommand("sort mileage");

        Command helpCommand = parser.parseCommand("help");

        Command fillCommand1 = parser.parseCommand("fill file test.txt");
        Command fillCommand2 = parser.parseCommand("fill console 10");
        Command fillCommand3 = parser.parseCommand("fill random 20");

        Command printCommand1 = parser.parseCommand("print console");
        Command printCommand2 = parser.parseCommand("print file test.txt");

        Command exitCommand = parser.parseCommand("exit");

        assertEquals(CommandType.SORT, sortCommand1.type);
        assertEquals(CommandType.SORT, sortCommand2.type);
        assertEquals("number", sortCommand1.params[0]);
        assertEquals("mileage", sortCommand2.params[0]);
        assertEquals("even", sortCommand1.params[1]);

        assertEquals(CommandType.HELP, helpCommand.type);

        assertEquals(CommandType.FILL, fillCommand1.type);
        assertEquals(CommandType.FILL, fillCommand2.type);
        assertEquals(CommandType.FILL, fillCommand3.type);
        assertEquals("file", fillCommand1.params[0]);
        assertEquals("test.txt", fillCommand1.params[1]);
        assertEquals("console", fillCommand2.params[0]);
        assertEquals("10", fillCommand2.params[1]);
        assertEquals("random", fillCommand3.params[0]);
        assertEquals("20", fillCommand3.params[1]);

        assertEquals(CommandType.PRINT, printCommand1.type);
        assertEquals(CommandType.PRINT, printCommand2.type);
        assertEquals("console", printCommand1.params[0]);
        assertEquals("file", printCommand2.params[0]);
        assertEquals("test.txt", printCommand2.params[1]);

        assertEquals(CommandType.EXIT, exitCommand.type);
    }

    @Test
    void when_incorrectValuesPassed_then_nullReturned() { // TODO: добавить тесты команды count
        Command sortCommand1 = parser.parseCommand("sort inexistent_field");
        Command sortCommand2 = parser.parseCommand("sort mileage wrong_mode");

        Command fillCommand1 = parser.parseCommand("fill file");
        Command fillCommand2 = parser.parseCommand("fill console not_a_number");

        Command inexistentCommand = parser.parseCommand("not_a_command");

        assertNull(sortCommand1);
        assertNull(sortCommand2);
        assertNull(fillCommand1);
        assertNull(fillCommand2);
        assertNull(inexistentCommand);
    }
}
