package main.model;

import main.exceptions.StringValidationException;
import main.exceptions.UMValidationException;

public class Calculator {

    StatisticsRepo repo = new StatisticsRepo();
    Converter converter = new Converter();
    private int index = 0;

    public DistanceValue calculate(String s, String finalUm) throws StringValidationException, UMValidationException {

        long start = System.currentTimeMillis();

        validateString(s);

        DistanceValue prevResult = readDistanceValue(s);

        if (index < s.length() - 1) {
            DistanceValue nextNumber;
            String operation;
            DistanceValue result = new DistanceValue();

            while (index < s.length()) {
                operation = getOperation(s);
                nextNumber = readDistanceValue(s);
                result = compute(prevResult, nextNumber, operation);
                prevResult = result;
            }

            print(s, result, finalUm);

            repo.addNewStatisticsEntry("calculate", start, System.currentTimeMillis());
            repo.print();

            return result;

        } else {

            repo.addNewStatisticsEntry("calculate", start, System.currentTimeMillis());
            repo.print();

            print(s, prevResult, finalUm);
            return prevResult;
        }
    }


    public DistanceValue readDistanceValue(String s) throws UMValidationException {

        long start = System.currentTimeMillis();

        DistanceValue dv;
        double value = 0;
        String unitOfMeasure = "";

        if (index < s.length()) {
            while (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                value = value * 10 + Character.getNumericValue(s.charAt(index));
                index++;
            }

            while (index < s.length() && s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
                unitOfMeasure = unitOfMeasure + s.charAt(index);
                index++;
            }

            validateUnitOfMeasure(unitOfMeasure);
        }

        dv = new DistanceValue(value, unitOfMeasure);

        repo.addNewStatisticsEntry("readDistanceValue", start, System.currentTimeMillis());

        return dv;

    }

    public DistanceValue compute(DistanceValue dv1, DistanceValue dv2, String operation) {

        long start = System.currentTimeMillis();

        DistanceValue result = new DistanceValue();
        DistanceValue newDV;
        if (!dv1.getUnitOfMeasure().equals(dv2.getUnitOfMeasure())) {
            newDV = converter.convert(dv2, dv1.getUnitOfMeasure());
        } else newDV = dv2;
        switch (operation) {
            case "addition":
                result.setValue(dv1.getValue() + newDV.getValue());
                result.setUnitOfMeasure(dv1.getUnitOfMeasure());
                break;
            case "subtraction":
                result.setValue(dv1.getValue() - newDV.getValue());
                result.setUnitOfMeasure(dv1.getUnitOfMeasure());
                break;
        }

        repo.addNewStatisticsEntry("compute", start, System.currentTimeMillis());
        return result;
    }

    public String getOperation(String s) {

        long start = System.currentTimeMillis();

        if (index < s.length()) {
            switch (s.charAt(index)) {
                case '+':
                    index++;
                    repo.addNewStatisticsEntry("getOperation", start, System.currentTimeMillis());
                    return "addition";
                case '-':
                    index++;
                    repo.addNewStatisticsEntry("getOperation", start, System.currentTimeMillis());
                    return "subtraction";
            }
        }

        repo.addNewStatisticsEntry("getOperation", start, System.currentTimeMillis());
        return null;
    }

    private void print(String s, DistanceValue dv, String finalUm) {

        long start = System.currentTimeMillis();

        if (!dv.getUnitOfMeasure().equals(finalUm)) {
            DistanceValue convertedDv = converter.convert(dv, finalUm);
            dv = convertedDv;
        }
        System.out.println(s + "=" + dv.toString());
        repo.addNewStatisticsEntry("print", start, System.currentTimeMillis());
    }

    public void validateString(String s) throws StringValidationException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == 'm' || s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'k')) {

                repo.addNewStatisticsEntry("validateString", start, System.currentTimeMillis());

                throw new StringValidationException("The String you entered contains unsupported " +
                        "characters. Please review it and try again.");
            }
            repo.addNewStatisticsEntry("validateString", start, System.currentTimeMillis());
        }
    }

    public void validateUnitOfMeasure(String um) throws UMValidationException {

        long start = System.currentTimeMillis();

        if (!(um.equals("mm") || um.equals("cm") || um.equals("dm") || um.equals("m") || um.equals("km"))) {

            repo.addNewStatisticsEntry("validateUnitOfMeasure", start, System.currentTimeMillis());

            throw new UMValidationException("You have entered an unsupported unit of measure. " +
                    "The accepted ones are: mm, cm, m, km. Please review your input.");
        }
        repo.addNewStatisticsEntry("validateUnitOfMeasure", start, System.currentTimeMillis());
    }
}

