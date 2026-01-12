package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.input.FileReader;
import ru.aston.hometask.util.CustomArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadFile() throws IOException {
        Path filePath = tempDir.resolve("test_buses.txt");
        List<String> lines = List.of("A777AA777 Mercedes 1000", "INVALID_DATA");
        Files.write(filePath, lines);

        FileReader reader = new FileReader();
        CustomArray<Bus> buses = reader.read(100, filePath.toString());

        assertEquals(1, buses.size());
        assertEquals("A777AA777", buses.get(0).getNumber());
        assertEquals("Mercedes", buses.get(0).getModel());
        assertEquals(1000, buses.get(0).getMileage());
    }
}
