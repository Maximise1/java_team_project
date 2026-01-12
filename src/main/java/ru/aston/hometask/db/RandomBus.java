package ru.aston.hometask.db;

import java.util.Random;

public class RandomBus {
        public static String randomNumber() {
            Random random = new Random();
            char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'}; //Массив букв для номера

            String number = letters[random.nextInt(letters.length)] + //Первая буква номера
                    String.format("%03d", random.nextInt(1000)) + //Три цифры номера
                    letters[random.nextInt(letters.length)] + //Вторая буква номера
                    letters[random.nextInt(letters.length)] + //Третья буква номера
                    String.format("%02d", random.nextInt(199) + 1); //Регион

            return number;
        }

        public static String randomModel() {
            Random random = new Random();
            String[] models = {"Ford", "UAZ", "Mercedes", "Honda"}; // Массив с моделями автобусов

            return models[random.nextInt(models.length)];
        }

        public static int randomMileage() {
            Random random = new Random();

            return random.nextInt(199999) + 1; //Пробег
        }
}
