package ru.aston.hometask.count;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class CounterHandler implements CommandHandler {

    private BusRepository repository;

    public CounterHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {
        var bus = new Bus(args[0], args[1], Integer.parseInt(args[2]));
        var buses = repository.getBuses();
        var busesSize = buses.size();

        long result = ForkJoinPool.commonPool().submit(() -> IntStream.range(0, busesSize)
                .parallel()
                .filter(i -> buses.get(i).equals(bus))
                .count()).join();

        System.out.println(result);
    }
}
