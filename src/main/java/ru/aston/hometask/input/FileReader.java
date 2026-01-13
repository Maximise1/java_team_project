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

            lines.map(line -> line.trim().split("\\|"))
                    .filter(parts -> parts.length == 4)
                    .filter(parts -> DataValidator.isValid(parts[1].trim(), parts[2].trim(), parts[3].trim()))
                    .map(parts -> new Bus.BusBuilder()
                            .number(parts[1].trim())
                            .model(parts[2].trim())
                            .mileage(Integer.parseInt(parts[3].trim()))
                            .build())
                    .forEach(buses::add);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return buses;
    }
}
