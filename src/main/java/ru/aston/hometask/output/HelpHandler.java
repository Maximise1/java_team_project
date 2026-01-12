package ru.aston.hometask.output;

import ru.aston.hometask.di.CommandHandler;

public class HelpHandler implements CommandHandler {

    @Override
    public void executeCommand(String[] args) {
        System.out.println("\nКоманды:");
        System.out.println("help - вывести список инструкций\n");
        System.out.println("fill - заполнить массив классами Автобус. Параметры:");
        System.out.println("    <Int> - *Не используется при режиме чтения из файла* размер массива (от 0 до 2,147,483,647)");
        System.out.println("    <String> - режим ввода (file, console, random)");
        System.out.println("    <String> - *Необходим при режиме чтения из файла* путь к файлу\n");
        System.out.println("print - выводит текущее содержимое массива");
        System.out.println("    <String> - режим вывода (file, console)");
        System.out.println("    <String> - *Необходим при выводе в файл* путь к файлу\n");
        System.out.println("sort - Производит сортировку массива. Параметры:");
        System.out.println("    number/model/mileage - Запустит сортировку по выбранному полю");
        System.out.println("    even - *Опциональный* Запустит сортировку только чётных значений выбранного поля\n");
        System.out.println("count - подсчитывает количество элементов с переданным значением");
        System.out.println("    <String> - номер автобуса");
        System.out.println("    <String> - модель автобуса");
        System.out.println("    <Int> - пробег автобуса\n");
        System.out.println("exit - выход из цикла программы\n");
    }
}
