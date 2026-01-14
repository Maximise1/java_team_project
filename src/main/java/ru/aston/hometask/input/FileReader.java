package ru.aston.hometask.input;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;
import ru.aston.hometask.util.DataValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader implements Reader { // TODO: реализовать чтение из файла

    @Override
    public CustomArray<Bus> read(int size, String params) {
        CustomArray<Bus> buses = new CustomArray<>();

        if (params == null || params.isEmpty()) {
            System.out.println("Ошибка: Путь к файлу не указан.");
            return buses;
        }

        try (Stream<String> lines = Files.lines(Paths.get(params))) {

            lines.map(line -> line.trim().split("\\s+"))
                    .filter(parts -> parts.length == 3)
                    .filter(parts -> DataValidator.isValid(parts[0], parts[1], parts[2]))
                    .map(parts -> new Bus.BusBuilder()
                            .number(parts[0])
                            .model(parts[1])
                            .mileage(Integer.parseInt(parts[2]))
                            .build())
                    .forEach(buses::add);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return buses;
    }
}
