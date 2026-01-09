package ru.aston.hometask.di;

import java.util.HashMap;
import java.util.Scanner;

import ru.aston.hometask.count.CounterHandler;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.input.InputHandler;
import ru.aston.hometask.input.CommandParser;
import ru.aston.hometask.output.OutputHandler;
import ru.aston.hometask.output.HelpHandler;
import ru.aston.hometask.sort.SortHandler;

public class Container {

    public Scanner scanner;
    public BusRepository repository;
    public CommandParser commandParser;

    private HashMap<CommandType, CommandHandler> handlers;

    public Container() {
        this.scanner = new Scanner(System.in);
        this.repository = new BusRepository();
        this.commandParser = new CommandParser();

        this.handlers = new HashMap<>();
        handlers.put(CommandType.COUNT, new CounterHandler(
                repository
        ));
        handlers.put(CommandType.SORT, new SortHandler(
                repository
        ));
        handlers.put(CommandType.FILL, new InputHandler(
                repository
        ));
        handlers.put(CommandType.HELP, new HelpHandler());
        handlers.put(CommandType.PRINT, new OutputHandler(
                repository
        ));
    }

    public CommandHandler getHandler(CommandType command) {
        return handlers.get(command);
    }
}
