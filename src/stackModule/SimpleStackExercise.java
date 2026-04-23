package stackModule;

import application.Exercise;
import java.util.Scanner;

public class SimpleStackExercise extends Exercise {

    public SimpleStackExercise(Scanner scanner) {
        super(scanner);
    }

    SimpleStack<String> stack;
    private int currentPhase = 0;
    private boolean firstTime = true;

    @Override
    protected void exerciseLogic() {
        try {
            switch (currentPhase) {
                case 0: menuLogic();     break;
                case 1: pushLogic();     break;
                case 2: popLogic();      break;
                case 3: peekLogic();     break;
                case 4: clearLogic();    break;
                case 5: running = false; break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
            currentPhase = 0;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("\nWelcome to the Stack exercise!");
            System.out.println("\nChoose stack implementation:");
            System.out.println("\nas: Array-based stack");
            System.out.println("\nls: Linked list-based stack");
            firstTime = false;

            String choice = scanner.nextLine();
            while (!choice.equals("as") && !choice.equals("ls")) {
                System.out.println("Invalid choice. Please enter 'as' or 'ls'.");
                choice = scanner.nextLine();
            }
            if (choice.equals("as")) {
                stack = new SimpleArrayStack<>();
            } else {
                stack = new SimpleLinkedStack<>();
            }
        } else {
            System.out.println("\n--- Stack State ---");
            System.out.println("Is empty (isEmpty): " + stack.isEmpty());
            System.out.println("Size (size): " + stack.size());
            System.out.println("-------------------");

            System.out.println("\nChoose an operation:");
            System.out.println("\np: push");
            System.out.println("\no: pop");
            System.out.println("\ne: peek");
            System.out.println("\nc: clear");
            System.out.println("\nmm: Main Menu");

            String choice = scanner.nextLine();
            while (!choice.equals("p") && !choice.equals("o") && !choice.equals("e") && !choice.equals("c") && !choice.equals("mm")) {
                System.out.println("Invalid choice. Please enter 'p', 'o', 'e' or 'c'.");
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "p":  currentPhase = 1; break;
                case "o":  currentPhase = 2; break;
                case "e":  currentPhase = 3; break;
                case "c":  currentPhase = 4; break;
                case "mm": currentPhase = 5; break;
                default: break;
            }
        }
    }

    private void pushLogic() {
        try {
            while (running) {
                System.out.println("\nEnter an element to push:");
                String element = scanner.nextLine();
                stack.push(element);
                System.out.println("\nKeep adding? (y/n): ");

                String remain = scanner.nextLine();
                while (!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep adding? (y/n): ");
                    remain = scanner.nextLine();
                }
                if (remain.equals("n")) running = false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        running = true;
        currentPhase = 0;
    }

    private void popLogic() {
        try {
            while (running) {
                String element = stack.pop();
                System.out.println("Popped element: " + element);
                System.out.println("\nKeep popping? (y/n): ");
                String remain = scanner.nextLine();
                while (!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep popping? (y/n): ");
                    remain = scanner.nextLine();
                }
                if (remain.equals("n")) running = false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        running = true;
        currentPhase = 0;
    }

    private void peekLogic() {
        try {
            String element = stack.peek();
            System.out.println("Top element: " + element);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = 0;
    }

    private void clearLogic() {
        stack.clear();
        System.out.println("Stack cleared.");
        currentPhase = 0;
    }
}