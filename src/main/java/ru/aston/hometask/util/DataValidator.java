package ru.aston.hometask.util;

import java.util.regex.Pattern;

public class DataValidator {
    private static final String BUS_NUMBER_REGEX = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";
    private static final Pattern BUS_NUMBER_PATTERN = Pattern.compile(BUS_NUMBER_REGEX);

    public static boolean isValid(String busNumber, String model, String mileageStr) {

        if (busNumber == null || model == null || mileageStr == null) {
            return false;
        }

        try {

            boolean isNumberValid = BUS_NUMBER_PATTERN.matcher(busNumber.toUpperCase().trim()).matches();
            boolean isModelValid = !model.trim().matches(".*\\d.*");
            int mil = Integer.parseInt(mileageStr.trim());
            boolean isMileageValid = mil >= 0;

            return isNumberValid && isModelValid && isMileageValid;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
