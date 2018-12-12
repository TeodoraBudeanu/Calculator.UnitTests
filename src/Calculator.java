import Exceptions.StringValidationException;
import Exceptions.UMValidationException;

public class Calculator {

    private static int index = 0;

    public static DistanceValue calculate(String s, String finalUm) {
        try {
            validateString(s);
        } catch (StringValidationException e) {
            System.out.println(e.getMessage());
        }
        DistanceValue prevResult = readDistanceValue(s);
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
        return result;
    }


    public static DistanceValue readDistanceValue(String s) {

        DistanceValue dv;
        double value = 0;
        String unitOfMeasure = "";

        if(index < s.length()) {
            while (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                value = value * 10 + Character.getNumericValue(s.charAt(index));
                index++;
            }

            while (index < s.length() && s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
                unitOfMeasure = unitOfMeasure + s.charAt(index);
                index++;
            }
            try {
                validateUnitOfMeasure(unitOfMeasure);
            } catch (UMValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        dv = new DistanceValue(value, unitOfMeasure);
        return dv;

    }

    public static DistanceValue compute(DistanceValue dv1, DistanceValue dv2, String operation) {
        DistanceValue result = new DistanceValue();
        DistanceValue newDV;
        if (!dv1.getUnitOfMeasure().equals(dv2.getUnitOfMeasure())) {
            newDV = Converter.convert(dv2, dv1.getUnitOfMeasure());
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
        return result;
    }

    public static String getOperation(String s) {


        if (index < s.length()) {
            switch (s.charAt(index)) {
                case '+':
                    index++;
                    return "addition";
                case '-':
                    index++;
                    return "subtraction";
                default:
                    System.out.println("Appropriate character was not found.");
            }
        }

        return null;
    }

    private static void print(String s, DistanceValue dv, String finalUm) {
        if (!dv.getUnitOfMeasure().equals(finalUm)) {
            DistanceValue convertedDv = Converter.convert(dv, finalUm);
            dv = convertedDv;
        }
        System.out.println(s + "=" + dv.toString());
    }

    public static void validateString(String s) throws StringValidationException {
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i)>='0' && s.charAt(i)<='9')||s.charAt(i) == '+'|| s.charAt(i) == '-'
                    || s.charAt(i) == 'm' || s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'k')){
                throw new StringValidationException("The String you entered contains unsupported " +
                        "characters. Please review it and try again.");
            }
        }
    }

    public static void validateUnitOfMeasure(String um) throws UMValidationException {
        if(!(um.equals("mm") || um.equals("cm") || um.equals("dm") || um.equals("m") || um.equals("km"))){
            throw new UMValidationException("You have entered an unsupported unit of measure. " +
                    "The accepted ones are: mm, cm, m, km. Please review your input.");
        }
    }

}

