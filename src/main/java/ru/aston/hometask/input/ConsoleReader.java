package ru.aston.hometask.input;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;
import ru.aston.hometask.util.DataValidator;

import java.util.Scanner;

public class ConsoleReader implements Reader { // TODO: сделать чтение автобусов из консоли

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public CustomArray<Bus> read(int size, String params) {
        CustomArray<Bus> buses = new CustomArray<>();

        System.out.println("Введите данные для " + size + " автобусов.");
        System.out.println("Формат: [Номер] [Модель] [Пробег]");
        System.out.println("Пример: А777КХ163 Volvo 50000");

        int count = 0;
        while (count < size) {
            System.out.print("Автобус №" + (count + 1) + ": ");
            String line = scanner.nextLine();
            String[] parts = line.trim().split("\\s+");

            if (parts.length != 3) {
                System.out.println("Ошибка: Нужно ввести 3 значения через пробел.");
                continue;
            }

            if (DataValidator.isValid(parts[0], parts[1], parts[2])) {
                Bus bus = new Bus.BusBuilder()
                        .number(parts[0])
                        .model(parts[1])
                        .mileage(Integer.parseInt(parts[2]))
                        .build();

                buses.add(bus);
                count++;
            } else {
                System.out.println("Ошибка: Данные не прошли валидацию. Проверьте формат.");
            }
        }

        return buses;
    }
}
