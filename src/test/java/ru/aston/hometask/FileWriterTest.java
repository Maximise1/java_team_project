package ru.aston.hometask;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.db.Bus;
import ru.aston.hometask.output.FileWriter;
import ru.aston.hometask.util.CustomArray;

public class FileWriterTest {
    @Test
    void whenFileWriterWritethenCreateFileWithTable(){
        CustomArray<Bus> buses = new CustomArray<>();
        FileWriter fw = new FileWriter();

        for(int i = 5; i >= 0; i--) {
            buses.add(new Bus.BusBuilder().build());
        }

        fw.write(buses, "BusesTableTest.txt");
    }
}
