package ru.aston.hometask.sort;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class SortHandler implements CommandHandler {

    private BusRepository repository;

    public SortHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {

    }
}
