package ru.aston.hometask.output;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class OutputHandler implements CommandHandler {

    private BusRepository repository;

    public OutputHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {
        new ConsolePrinter().print(repository.getBuses());
        new FileWriter().write(repository.getBuses(),"BusesTable.txt");
    }
}
