package ru.aston.hometask.util;

import java.util.Arrays;

public class CustomArray<Bus> {

    private Object[] data;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public CustomArray() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        data = new Object[capacity];
        size = 0;
    }

    public void add(Bus element) {
        ensureCapacity();
        data[size++] = element;
    }

    public Bus get(int index) {
        checkIndex(index);
        return (Bus) data[index];
    }

    public void set(int index, Bus element) {
        checkIndex(index);
        data[index] = element;
    }

    public Bus remove(int index) {
        checkIndex(index);
        Bus removed = (Bus) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        data[--size] = null; // prevent memory leak
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    public CustomArray<Bus> copy() {
        CustomArray<Bus> copy = new CustomArray<>(this.data.length);
        copy.size = this.size;
        System.arraycopy(this.data, 0, copy.data, 0, this.size);
        return copy;
    }
}