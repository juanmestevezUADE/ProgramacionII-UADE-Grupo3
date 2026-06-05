package application;
import java.util.Scanner;

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
                exploreFactorial(n);
                System.out.println("Factorial of " + n + " is: " + exploreFactorial(n));
                break;
            case "2":
                System.out.println("Enter a non-negative integer to compute its Fibonacci number:");
                int m = Integer.parseInt(scanner.nextLine());
                exploreFibonacci(m);
                System.out.println("Fibonacci of " + m + " is: " + m);
                break;
            case "3":
                System.out.println("Enter a non-negative integer to compute its exclusive sum:");
                int k = Integer.parseInt(scanner.nextLine());
                exploreExclusiveSum(k);
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
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 6.");
        }
    }

    private int exploreFactorial(int Factorial){
        /*Caso Base */
        if (Factorial == 0){
            return 1;
        }
        /*Caso Recursivo */
        return Factorial * exploreFactorial(Factorial - 1);
    }

    private int exploreFibonacci(int m){
        /*Caso base 0 */
        if(m == 0){
            return m;
        }
        /*Caso base 1 */
        if(m == 1){
            System.out.println("Fibonacci of " + m + " is: 1");
            return m;
        }
        /*Caso Recursivo */
        return exploreFibonacci(m - 1) + exploreFibonacci(m - 2);
        
        
    }

    private int exploreExclusiveSum(int k){
        if (k == 0){
            return 0;
        }
        return k -1  + exploreExclusiveSum(k - 1);
    }

    private int explorePyramid(int height, int space) {
    if (height == 0) {
        return 0;
    }

    // Primero baja hasta el caso base...
    explorePyramid(height - 1, space + 1);

    // ...y al volver imprime (de menor a mayor)
    System.out.println();
    for (int i = 0; i < space; i++) {
        System.out.print(" ");
    }
    for (int j = 0; j < 2 * height - 1; j++) {
        System.out.print("*");
    }

    return 0;
    }

}