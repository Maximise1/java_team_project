package ru.aston.hometask.input;

import java.util.HashMap;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class InputHandler implements CommandHandler { // TODO: Вызывает методы классов BusConsoleReader и BusFileReader с корректными аргументами

    private BusRepository repository;
    private HashMap<String, Reader> readers;

    public InputHandler(BusRepository newRepository) {
        this.repository = newRepository;

        this.readers = new HashMap<>();
        readers.put("console", new ConsoleReader());
        readers.put("file", new FileReader());
    }

    @Override
    public void executeCommand(String[] args) {

    }
}
