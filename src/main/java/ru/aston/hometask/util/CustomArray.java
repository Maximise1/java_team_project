package ru.aston.hometask.util;

import java.util.Arrays;

public class CustomArray<T> { // TODO: Переделать, так как эта реализация сгенерирована чатом гпт

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

    // Add element to the end
    public void add(T element) {
        ensureCapacity();
        data[size++] = element;
    }

    // Get element at index
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    // Replace element at index
    public void set(int index, T element) {
        checkIndex(index);
        data[index] = element;
    }

    // Remove element at index
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        data[--size] = null; // prevent memory leak
        return removed;
    }

    // Current number of elements
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

    public CustomArray<T> copy() {
        CustomArray<T> copy = new CustomArray<>(this.data.length);
        copy.size = this.size;
        System.arraycopy(this.data, 0, copy.data, 0, this.size);
        return copy;
    }
}