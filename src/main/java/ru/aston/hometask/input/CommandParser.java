package ru.aston.hometask.input;

import ru.aston.hometask.di.Command;
import ru.aston.hometask.di.CommandType;

import java.util.Arrays;

public class CommandParser { // TODO: наш парсер. Возвращает null в типе команды, если введено некорректное значение

    public Command parseCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            System.out.println("Ошибка: Пустая команда.");
            return null;
        }

        String[] parts = command.trim().split("\\s+");
        String commandName = parts[0].toUpperCase();

        String[] params = parts.length > 1
                ? Arrays.copyOfRange(parts, 1, parts.length)
                : new String[0];

        try {
            CommandType type = CommandType.valueOf(commandName);
            return new Command(type, params);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Команда '" + commandName + "' не распознана.");
            return null;
        }
    }
}
