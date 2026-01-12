package ru.aston.hometask.input;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;

public interface Reader {

    CustomArray<Bus> read(int size, String params);
}
