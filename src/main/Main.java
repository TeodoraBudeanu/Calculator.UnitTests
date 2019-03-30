package main;

import main.exceptions.StringValidationException;
import main.exceptions.UMValidationException;
import main.model.Calculator;

public class Main {
    public static void main(String[] args) throws StringValidationException, UMValidationException {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please insert the string you want to calculate and then the Unit Of Measure " +
//                "for the result.");
//        main.model.Calculator.calculate(sc.nextLine(), sc.nextLine());

        Calculator calculator = new Calculator();
        calculator.calculate("20m+1km-30000cm", "m");
    }
}
