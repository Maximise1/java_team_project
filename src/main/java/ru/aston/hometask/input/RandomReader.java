package ru.aston.hometask.input;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;

import java.util.stream.IntStream;

public class RandomReader implements Reader {
    @Override
    public CustomArray<Bus> read(int size, String params) {
        CustomArray<Bus> buses = new CustomArray<>();

        IntStream.range(0, size)
                .mapToObj(i -> new Bus.BusBuilder()
                        .number(Bus.randomNumber())
                        .model(Bus.randomModel())
                        .mileage(Bus.randomMileage())
                        .build())
                .forEach(buses::add);

        System.out.println("Сгенерировано случайных автобусов: " + buses.size());
        return buses;
    }
}
