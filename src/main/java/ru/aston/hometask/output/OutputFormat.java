package ru.aston.hometask.output;

import ru.aston.hometask.util.CustomArray;
import ru.aston.hometask.db.Bus;

public class OutputFormat {
    public static String getOutputFormat(CustomArray<Bus> buses) {
        StringBuilder sb = new StringBuilder();

        sb.append("+======================+======================+==========+\n");
        sb.append("|       Номер          |      Модель          |  Пробег  |\n");
        sb.append("+======================+======================+==========+\n");

        for (int i = 0; i < buses.size(); i++) {
            sb.append(String.format("| %-20s | %-20s | %8d |%n",
                    truncate(buses.get(i).getNumber(), 20),
                    truncate(buses.get(i).getModel(), 20),
                    buses.get(i).getMileage()));
        }

        sb.append("+======================+======================+==========+");

        return sb.toString();
    }

    private static String truncate(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }
}
