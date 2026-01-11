package ru.aston.hometask.output;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;

public class ConsolePrinter {

    public void print(CustomArray<Bus> buses) {
        System.out.println(OutputFormat.getOutputFormat(buses));
    }
}
