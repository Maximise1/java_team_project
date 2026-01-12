package ru.aston.hometask.input;

import java.util.Objects;
import java.util.Set;

import ru.aston.hometask.di.Command;
import ru.aston.hometask.di.CommandType;

public class CommandParser {

    private static final Set<String> SORT_FIELDS =
            Set.of("number", "model", "mileage");

    private static final Set<String> FILL_MODES =
            Set.of("file", "console", "random");

    private static final Set<String> PRINT_MODES =
            Set.of("file", "console");

    public Command parseCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }

        String[] splitCommand = command.trim().split("\\s+");

        CommandType type;
        try {
            type = CommandType.valueOf(splitCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Неизвестная команда: " + splitCommand[0]);
            return null;
        }

        return switch (type) {
            case EXIT -> new Command(CommandType.EXIT, new String[0]);
            case HELP -> new Command(CommandType.HELP, new String[0]);
            case PRINT -> parsePrint(command, splitCommand);
            case SORT -> parseSort(splitCommand);
            case FILL -> parseFill(command, splitCommand);
            case COUNT -> parseCount(splitCommand);
        };
    }

    private Command parseCount(String[] splitCommand) {
        if (splitCommand.length != 4) {
            System.out.println(
                    "Команда count должна соответствовать формату print <number> <model> <mileage>");
            System.out.println("Пример: count А111AA11 ford 100");
            return null;
        }

        if (DataValidator.isValid(splitCommand[1], splitCommand[2], splitCommand[3])) {
            return new Command(
                    CommandType.COUNT,
                    new String[] {splitCommand[1], splitCommand[2], splitCommand[3]}
            );
        } else {
            System.out.println(
                    "Команда count должна соответствовать формату print <number> <model> <mileage>");
            System.out.println("Пример: count А111AA11 ford 100");
            return null;
        }
    }

    private Command parsePrint(String originalCommand, String[] splitCommand) {
        if ((splitCommand.length < 2) || (splitCommand.length > 3)) {
            System.out.println("Неверный синтаксис команды print.");
            System.out.println("Пример: print file test.txt");
            System.out.println("Пример: print console");
            return null;
        }

        if (!PRINT_MODES.contains(splitCommand[1])) {
            System.out.println("Ошибка. Неизвестный режим вывода: " + splitCommand[1]);
            return null;
        }

        if (Objects.equals(splitCommand[1], "file")) {
            String path = originalCommand
                    .substring(originalCommand.indexOf("file") + 4)
                    .trim();

            if (path.isEmpty()) {
                System.out.println("Ошибка. Не указан путь к файлу");
                return null;
            }

            return new Command(
                    CommandType.PRINT,
                    new String[]{ "file", path }
            );
        }

        return new Command(
                CommandType.PRINT,
                new String[] { splitCommand[1] }
        );
    }

    private Command parseSort(String[] splitCommand) {
        if (splitCommand.length < 2 || splitCommand.length > 3) {
            System.out.println("Неверный синтаксис команды sort.");
            System.out.println("Пример: sort number");
            System.out.println("Пример: sort mileage even");
            return null;
        }

        if (!SORT_FIELDS.contains(splitCommand[1])) {
            System.out.println("Ошибка. Неизвестное поле сортировки: " + splitCommand[1]);
            System.out.println("Доступные поля: number, model, mileage");
            return null;
        }

        if (splitCommand.length == 3 && !Objects.equals(splitCommand[2], "even")) {
            System.out.println("Ошибка. Неизвестный параметр сортировки: " + splitCommand[2]);
            return null;
        }

        if (splitCommand.length == 3) {
            return new Command(
                    CommandType.SORT,
                    new String[]{ splitCommand[1], "even" }
            );
        } else {
            return new Command(
                    CommandType.SORT,
                    new String[]{ splitCommand[1]}
            );
        }
    }

    private Command parseFill(String originalCommand, String[] splitCommand) {
        if (splitCommand.length < 3) {
            System.out.println("Неверный синтаксис команды fill.");
            System.out.println("Пример: fill file test.txt");
            System.out.println("Пример: fill console 10");
            return null;
        }

        if (!FILL_MODES.contains(splitCommand[1])) {
            System.out.println("Ошибка. Неизвестный режим заполнения: " + splitCommand[1]);
            return null;
        }

        if (Objects.equals(splitCommand[1], "file")) {
            String path = originalCommand
                    .substring(originalCommand.indexOf("file") + 4)
                    .trim();

            if (path.isEmpty()) {
                System.out.println("Ошибка. Не указан путь к файлу");
                return null;
            }

            return new Command(
                    CommandType.FILL,
                    new String[]{ "file", path }
            );
        }

        try {
            int amount = Integer.parseInt(splitCommand[2]);
            if (amount <= 0) {
                throw new NumberFormatException();
            }
            return new Command(
                    CommandType.FILL,
                    new String[]{ splitCommand[1], splitCommand[2] }
            );
        } catch (NumberFormatException e) {
            System.out.println(
                    "Ошибка. После режима заполнения необходимо указать " +
                            "количество элементов массива от 1 до 2,147,483,647"
            );
            return null;
        }
    }
}
