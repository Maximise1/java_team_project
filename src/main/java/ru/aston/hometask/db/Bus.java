package ru.aston.hometask.db;

import java.util.Objects;

public class Bus extends RandomBus{ // TODO: Добавить реализацию с паттерном Builder
    private final String number;
    private final String model;
    private final int mileage;

    public Bus(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    //--------------------------------------
    //Геттеры для дальнейшей работы с сортировкой по полям
    public String getNumber() {
        return this.number;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }
    //--------------------------------------

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    public static class BusBuilder {
        //Рандомные значения по умолчанию
        private String number = randomNumber();
        private String model = randomModel();
        private int mileage = randomMileage();

        public BusBuilder number(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder model(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return mileage == bus.mileage &&
                Objects.equals(number, bus.number) &&
                Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }
}
