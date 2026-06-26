package application;

import java.util.Scanner;

// Ejercicio que demuestra el uso de recursión a través de distintas funciones clásicas.
public class RecursionExercise extends Exercise {

    public RecursionExercise(Scanner scnr) {
        super(scnr);
        this.scanner = scnr;
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("Choose a recursive function to explore:");
        System.out.println("1: Factorial");
        System.out.println("2: Fibonacci");
        System.out.println("3: Exclusive sum");
        System.out.println("4: Pyramid");
        System.out.println("5: Palindrome check");
        System.out.println("6: Back to main menu");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter a non-negative integer to compute its factorial:");
                int n = Integer.parseInt(scanner.nextLine());
                // Se llama una sola vez y se muestra el resultado
                System.out.println("Factorial of " + n + " is: " + exploreFactorial(n));
                break;
            case "2":
                System.out.println("Enter a non-negative integer to compute its Fibonacci number:");
                int m = Integer.parseInt(scanner.nextLine());
                System.out.println("Fibonacci of " + m + " is: " + exploreFibonacci(m));
                break;
            case "3":
                System.out.println("Enter a non-negative integer to compute its exclusive sum:");
                int k = Integer.parseInt(scanner.nextLine());
                System.out.println("Exclusive sum of " + k + " is: " + exploreExclusiveSum(k));
                break;
            case "4":
                System.out.println("Enter the height of the pyramid:");
                int height = Integer.parseInt(scanner.nextLine());
                explorePyramid(height, 0);
                break;
            case "6":
                System.out.println("Returning to main menu...");
                running = false;
                break; // sin este break caía al default mostrando "Invalid choice"
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 6.");
        }
    }

    // Calcula el factorial de n de forma recursiva.
    // Caso base: factorial(0) = 1
    // Caso recursivo: factorial(n) = n * factorial(n-1)
    private int exploreFactorial(int Factorial) {
        if (Factorial == 0) {
            return 1;
        }
        return Factorial * exploreFactorial(Factorial - 1);
    }

    // Calcula el n-ésimo número de Fibonacci de forma recursiva.
    // Caso base: fib(0) = 0, fib(1) = 1
    // Caso recursivo: fib(n) = fib(n-1) + fib(n-2)
    private int exploreFibonacci(int m) {
        if (m == 0) {
            return m;
        }
        if (m == 1) {
            return m;
        }
        return exploreFibonacci(m - 1) + exploreFibonacci(m - 2);
    }

    // Calcula la suma exclusiva: 0 + 1 + 2 + ... + (k-1)
    // Es decir, la suma de todos los enteros anteriores a k.
    private int exploreExclusiveSum(int k) {
        if (k == 0) {
            return 0;
        }
        return k - 1 + exploreExclusiveSum(k - 1);
    }

    // Imprime una pirámide de asteriscos de forma recursiva.
    // Primero llega al caso base (height == 0) y después imprime al volver,
    // de modo que la fila más pequeña queda arriba y la más grande abajo.
    private int explorePyramid(int height, int space) {
        if (height == 0) {
            return 0;
        }

        // Bajar hasta el caso base antes de imprimir (para que salga de abajo hacia arriba)
        explorePyramid(height - 1, space + 1);

        // Al volver de la recursión, imprimir la fila actual
        System.out.println();
        for (int i = 0; i < space; i++) {
            System.out.print(" "); // sangría para centrar la fila
        }
        for (int j = 0; j < 2 * height - 1; j++) {
            System.out.print("*"); // asteriscos de la fila
        }

        return 0;
    }
}
