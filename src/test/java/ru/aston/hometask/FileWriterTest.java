package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.output.FileWriter;
import ru.aston.hometask.output.OutputFormat;
import ru.aston.hometask.util.CustomArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileWriterTest {
    @Test
    void when_FileWriterWriteCalled_then_CreateFileWithTable() throws IOException {
        CustomArray<Bus> buses = new CustomArray<>();
        FileWriter fw = new FileWriter();
        String fileName = "BusesTableTest.txt";
        Path filePath = Paths.get(fileName);

        for(int i = 5; i >= 0; i--) {
            buses.add(new Bus.BusBuilder().build());
        }

        try {
            fw.write(buses, fileName);

            assertTrue(Files.exists(filePath), "Файл должен быть создан");
            assertTrue(Files.size(filePath) > 0, "Файл не должен быть пустым");
        }
        finally {
            Files.deleteIfExists(filePath);
        }
    }
}
