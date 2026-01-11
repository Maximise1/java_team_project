package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.output.ConsolePrinter;
import ru.aston.hometask.util.CustomArray;

public class ConsolePrinterTest {
    @Test
    void whenConsolePrinterthenPrintConsoleTable() {
        CustomArray<Bus> buses = new CustomArray<>();
        ConsolePrinter cp = new ConsolePrinter();

        for(int i = 5; i >= 0; i--) {
            buses.add(new Bus.BusBuilder().build());
        }

        cp.print(buses);
    }
}
