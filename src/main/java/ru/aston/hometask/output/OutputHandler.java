package ru.aston.hometask.output;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class OutputHandler implements CommandHandler { // TODO: Реализовать вывод списка автобусов в консоль и в файл

    private BusRepository repository;

    public OutputHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {

    }
}
