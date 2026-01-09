package ru.aston.hometask;

import ru.aston.hometask.di.CommandHandler;
import ru.aston.hometask.di.CommandType;
import ru.aston.hometask.di.Container;
import ru.aston.hometask.di.Command;

public class Application {

    private Container container;
    private Boolean exitSignal = false;

    public Application() {
        this.container = new Container();
    }

    public void run() {
        System.out.println("Программа для сортировки классов Автобус");
        container.getHandler(CommandType.HELP).executeCommand(null);

        while (!exitSignal) {
            System.out.println("Введите команду:");

            String input = container.scanner.nextLine();
            Command parsedCommand = container.commandParser.parseCommand(input);

            if (parsedCommand == null) {
                continue;
            } else if (parsedCommand.type == CommandType.EXIT) {
                exitSignal = true;
                continue;
            }

            CommandHandler handler = container.getHandler(parsedCommand.type);
            handler.executeCommand(parsedCommand.params);
        }
    }
}
