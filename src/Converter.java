public class Converter {

    private static String[][] convertorTable = {
            {"", "mm", "cm", "dm", "m", "km"},
            {"mm", "1", "0.1", "0.01", "0.001", "0.000001"},
            {"cm", "10", "1", "0.1", "0.01", "0.00001"},
            {"dm", "100", "10", "1", "0.1", "0.0001"},
            {"m", "1000", "100", "10", "1", "0.001"},
            {"km", "1000000", "100000", "10000", "1000", "1"}};

    public static Double getCoefficientValue(String initialUnitOfMeasure, String toBeConvertedIn) {
        Integer x = getInitialUMIndex(initialUnitOfMeasure);
        Integer y = getWantedUMIndex(toBeConvertedIn);
        return Double.parseDouble(convertorTable[x][y]);
    }

    public static DistanceValue convert(DistanceValue distanceValue, String wantedUM) {
        Double coefficient = getCoefficientValue(distanceValue.getUnitOfMeasure(), wantedUM);
        double newValue = distanceValue.getValue() * coefficient;
        distanceValue.setValue(newValue);
        distanceValue.setUnitOfMeasure(wantedUM);
        return distanceValue;
    }

    private static Integer getInitialUMIndex(String initialUnitOfMeasure) {

        for (int i = 0; i < convertorTable.length; i++) {
            if (convertorTable[i][0].equals(initialUnitOfMeasure)) {
                return i;
            }
        }
        return null;
    }

    private static Integer getWantedUMIndex(String toBeConvertedIn) {
        for (int i = 0; i < convertorTable[0].length; i++) {
            if (convertorTable[0][i].equals(toBeConvertedIn)) {
                return i;
            }
        }
        return null;
    }
}

