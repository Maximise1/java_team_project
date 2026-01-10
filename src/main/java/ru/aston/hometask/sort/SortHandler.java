package ru.aston.hometask.sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.db.BusRepository;
import ru.aston.hometask.di.CommandHandler;

public class SortHandler implements CommandHandler {

    private Sorter sorter = new Sorter();
    private BusRepository repository;
    private HashMap<String, Comparator<Bus>> busComparators;

    public SortHandler(BusRepository newRepository) {
        this.repository = newRepository;

        busComparators = new HashMap<>();
        busComparators.put("number", Comparator.comparing(Bus::getNumber));
        busComparators.put("model", Comparator.comparing(Bus::getModel));
        busComparators.put("mileage", Comparator.comparing(Bus::getMileage));
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 1) {
            System.out.println(
                    "Ошибка. Не передано поле по которому необходимо произвести сортировку.");
            return;
        }

        if (args.length == 1) {
            if (Objects.equals(args[0], "number") ||
                Objects.equals(args[0], "model") ||
                Objects.equals(args[0], "mileage")
            ) {
                repository.setBuses(sorter.sort(
                        repository.getBuses(),
                        false,
                        busComparators.get(args[0]),
                        null
                ));
                System.out.println("Массив автобусов отсортирован по полю " + args[0]);
            } else {
                System.out.println("Ошибка. Поле " + args[0] + " не найдено.");
            }
        } else if (args.length == 2 && Objects.equals(args[1], "even")) {
            if (Objects.equals(args[0], "mileage")) {
                repository.setBuses(sorter.sort(
                        repository.getBuses(),
                        true,
                        busComparators.get(args[0]),
                        bus -> bus.getMileage() % 2 == 0
                ));
                System.out.println("Элементы с четным значением поля mileage отсортированы.");
            } else {
                System.out.println(
                        "Ошибка. Поле " + args[0] + " не может быть отсортировано в четном режиме.");
            }
        } else {
            System.out.println("Ошибка. Слишком много аргументов для команды sort.");
        }
    }
}
