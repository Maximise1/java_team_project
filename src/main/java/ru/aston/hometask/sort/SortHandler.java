package ru.aston.hometask.sort;

import java.util.Comparator;
import java.util.Objects;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class SortHandler implements CommandHandler {

    private Sorter sorter = new Sorter();
    private BusRepository repository;

    public static class BusComparators {

        private BusComparators() {}
        public static final Comparator<Bus> BY_MODEL = Comparator.comparing(Bus::getModel);
        public static final Comparator<Bus> BY_NUMBER = Comparator.comparing(Bus::getNumber);
        public static final Comparator<Bus> BY_MILEAGE = Comparator.comparing(Bus::getMileage);
    }

    public SortHandler(BusRepository newRepository) {
        this.repository = newRepository;
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка. Не передано поле по которому необходимо произвести сортировку.");
            return;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "number":
                    repository.setBuses(sorter.sort(
                        repository.getBuses(),
                        false,
                        BusComparators.BY_NUMBER,
                        null
                    ));
                    break;
                case "model":
                    repository.setBuses(sorter.sort(
                            repository.getBuses(),
                            false,
                            BusComparators.BY_MODEL,
                            null
                    ));
                    break;
                case "mileage":
                    repository.setBuses(sorter.sort(
                            repository.getBuses(),
                            false,
                            BusComparators.BY_MILEAGE,
                            null
                    ));
                    break;
                default:
                    System.out.println("Ошибка. Поле " + args[0] + " не найдено.");
            }
        } else if (args.length == 2 && Objects.equals(args[1], "even")) {
            switch (args[0]) {
                case "number":
                    System.out.println("Ошибка. Поле number не может быть отсортировано в четном режиме.");
                    break;
                case "model":
                    System.out.println("Ошибка. Поле model не может быть отсортировано в четном режиме.");
                    break;
                case "mileage":
                    repository.setBuses(sorter.sort(
                            repository.getBuses(),
                            true,
                            BusComparators.BY_MILEAGE,
                            bus -> bus.getMileage() % 2 == 0
                    ));
                    break;
                default:
                    System.out.println("Ошибка. Поле " + args[0] + " не найдено.");
            }
        } else {
            System.out.println("Ошибка. Слишком много аргументов для команды sort.");
        }
    }
}
