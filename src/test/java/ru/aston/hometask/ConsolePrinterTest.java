package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.output.ConsolePrinter;
import ru.aston.hometask.output.OutputFormat;
import ru.aston.hometask.util.CustomArray;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsolePrinterTest {
    @Test
    void when_ConsolePrinterCalledPrint_then_PrintTableInConsole() {
        CustomArray<Bus> buses = new CustomArray<>();
        ConsolePrinter cp = new ConsolePrinter();

        for(int i = 5; i >= 0; i--) {
            buses.add(new Bus.BusBuilder().build());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            cp.print(buses);
            String output = outputStream.toString();

            assertNotNull(output, "Вывод не существует");
            assertFalse(output.trim().isEmpty(), "Вывод пуст");
            assertEquals(OutputFormat.getOutputFormat(buses) + System.lineSeparator(), output, "Данные не сходятся");

        } finally {
            System.setOut(originalOut);
        }
    }
}
