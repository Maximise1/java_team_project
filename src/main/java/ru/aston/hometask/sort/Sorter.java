package ru.aston.hometask.sort;

import java.util.Comparator;
import java.util.function.Predicate;

import ru.aston.hometask.db.Bus;
import ru.aston.hometask.util.CustomArray;

public class Sorter {

    private <T> int partition(CustomArray<T> list, int low, int high, Comparator<? super T> cmp) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (cmp.compare(list.get(j), pivot) < 0) {
                i += 1;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    public <T> void quickSort(
            CustomArray<T> list,
            int low,
            int high,
            Comparator<? super T> cmp
    ) {
        if (low < high) {
            int pi = partition(list, low, high, cmp);
            quickSort(list, low, pi - 1, cmp);
            quickSort(list, pi + 1, high, cmp);
        }
    }

    private <T> void swap(CustomArray<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public <T> void quickSortEven(
            CustomArray<T> arr,
            Predicate<? super T> isEven,
            Comparator<? super T> cmp
    ) {
        CustomArray<Integer> indexes = new CustomArray<>();
        CustomArray<T> evens = new CustomArray<>();

        for (int i = 0; i < arr.size(); i++) {
            if (isEven.test(arr.get(i))) {
                indexes.add(i);
                evens.add(arr.get(i));
            }
        }

        quickSort(evens, 0, evens.size()-1, cmp);

        for (int i = 0; i < indexes.size(); i++) {
            arr.set(indexes.get(i), evens.get(i));
        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }

    public CustomArray<Bus> sort(
            CustomArray<Bus> buses,
            Boolean evenSortMode,
            Comparator<? super Bus> comparator,
            Predicate<? super Bus> predicate
    ) {
        CustomArray<Bus> copy = buses.copy();

        if (!evenSortMode) {
            quickSort(copy, 0, copy.size() - 1, comparator);
        } else {
            quickSortEven(
                    copy,
                    predicate,
                    comparator
            );
        }

        return copy;
    }
}
