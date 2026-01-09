package ru.aston.hometask.count;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class CounterHandler implements CommandHandler { // TODO: Реализовать многопоточный метод, подсчитывающий количество вхождений элемента N в коллекцию и выводящий результат в консоль.

    private BusRepository repository;

    public CounterHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {

    }
}
