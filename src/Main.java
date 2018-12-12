import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert the string you want to calculate and then the Unit Of Measure " +
                "for the result.");
        Calculator.calculate(sc.nextLine(), sc.nextLine());
    }
}
