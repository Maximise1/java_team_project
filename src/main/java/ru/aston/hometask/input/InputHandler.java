package ru.aston.hometask.input;

import java.util.HashMap;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;
import ru.aston.hometask.util.CustomArray;

public class InputHandler implements CommandHandler { // TODO: Вызывает методы классов BusConsoleReader и BusFileReader с корректными аргументами

    private BusRepository repository;
    private HashMap<String, Reader> readers;

    public InputHandler(BusRepository newRepository) {
        this.repository = newRepository;

        this.readers = new HashMap<>();
        readers.put("console", new ConsoleReader());
        readers.put("file", new FileReader());
        readers.put("random", new RandomReader());
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: Недостаточно аргументов для FILL.");
            return;
        }

        try {
            int size = Integer.parseInt(args[0]);
            String mode = args[1].toLowerCase();
            String path = (args.length > 2) ? args[2] : null;

            Reader selectedReader = readers.get(mode);
            if (selectedReader != null) {
                CustomArray<Bus> buses = selectedReader.read(size, path);
                repository.addBuses(buses);

                System.out.println("Успешно добавлено " + size + " элементов.");
            } else {
                System.out.println("Ошибка: Неизвестный режим ввода '" + mode + "' Доступны: file, console, random");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Первый параметр (размер) должен быть числом.");
        }
    }
}
