package ru.aston.hometask.input;

import ru.aston.hometask.di.Command;
import ru.aston.hometask.di.CommandType;

public class CommandParser { // TODO: наш парсер. Возвращает null в типе команды, если введено некорректное значение

    public Command parseCommand(String command) {
        return new Command(CommandType.EXIT, new String[0]);
    }
}
