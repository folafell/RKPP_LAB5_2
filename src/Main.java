import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите выражение (или 'exit' для выхода):");
            String expression = scanner.nextLine();

            if ("exit".equalsIgnoreCase(expression)) {
                System.out.println("Выход из программы...");
                break;
            }

            double result = calculator.calculate(expression);
            System.out.println("Результат: " + result);
        }

        scanner.close();
    }
}