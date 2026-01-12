package ru.aston.hometask.output;

import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class OutputHandler implements CommandHandler {

    private BusRepository repository;
    private final String consoleArgs = "console";
    private final String fileArgs = "file";

    public OutputHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {
        if(args.length > 0 && args[0].equals(consoleArgs)) {
            ConsolePrinter.print(repository.getBuses());
        }
        else if(args.length == 2 && args[0].equals(fileArgs)){
            FileWriter.write(repository.getBuses(), args[1]);
        }
        else {
            System.out.println("Ошибка ввода аргументов!");
            //вернуть в меню
        }
    }
}
