package ru.aston.hometask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleTestUtil {
    public static ByteArrayOutputStream interceptOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }
}
