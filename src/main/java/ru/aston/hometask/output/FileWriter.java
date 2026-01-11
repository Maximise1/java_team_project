package ru.aston.hometask.output;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;

import java.io.IOException;

public class FileWriter {
    public void write(
            CustomArray<Bus> buses,
            String path
    ) {
        writeBusesTable(path, OutputFormat.getOutputFormat(buses));
    }

    public static void writeBusesTable(String fileName, String table) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName, true)) {
            writer.write("\nОТЧЕТ ПО АВТОБУСАМ\n");
            writer.write("Дата формирования: " + java.time.LocalDate.now() + "\n\n");

            writer.write(table);

        } catch (IOException e) {
            System.err.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
