package ru.aston.hometask.output;

import ru.aston.hometask.di.CommandHandler;

public class HelpHandler implements CommandHandler {

    @Override
    public void executeCommand(String[] args) {
        System.out.println("\nКоманды:");
        System.out.println("help - вывести список инструкций\n");
        System.out.println("fill - заполнить массив классами Автобус. Параметры:");
        System.out.println("    <Int> - *Не используется при режиме чтения из файла* размер массива (от 0 до 2,147,483,647)");
        System.out.println("    <String> - режим ввода (file, console, random)\n");
        System.out.println("    <String> - *Необходим при режиме чтения из файла* путь к файлу");
        System.out.println("print - выводит текущее содержимое массива\n");
        System.out.println("sort - Производит сортировку массива. Параметры:");
        System.out.println("    number/model/mileage - Запустит сортировку по выбранному полю");
        System.out.println("    even - *Опциональный* Запустит сортировку только чётных значений выбранного поля\n");
        System.out.println("count - подсчитывает количество элементов с переданным значением выбранного поля");
        System.out.println("    number/model/mileage - поле");
        System.out.println("    <Object> - значение\n");
        System.out.println("exit - выход из цикла программы\n");
    }
}
