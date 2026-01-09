package ru.aston.hometask.db;

import ru.aston.hometask.util.CustomArray;

public class BusRepository {

    private CustomArray<Bus> buses = new CustomArray<>();

    public CustomArray<Bus> getBuses() {
        return buses.copy();
    }

    public void setBuses(CustomArray<Bus> newBuses) {
        this.buses = newBuses;
    }

    public void addBuses(CustomArray<Bus> newBuses) {
        for (int i = 0; i < newBuses.size(); i++) {
            this.buses.add(newBuses.get(i));
        }
    }

    public void addBus(Bus bus) {
        this.buses.add(bus);
    }

    public void deleteBuses() {
        this.buses = new CustomArray<>();
    }
}
