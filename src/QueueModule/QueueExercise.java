package QueueModule;
import application.Exercise;

import java.util.Scanner;


public class QueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    SimpleQueue<String> queue;
    private Scanner scanner;

    public QueueExercise(Scanner scnr) {
        super(scnr);
        this.scanner = scnr;
    }

    @Override
    protected void exerciseLogic() {
        try {
            if (currentPhase == 0) {
                if (firstTime) {
                    System.out.println("\nWelcome to the Queue exercise!");
                    System.out.println("\nChoose queue implementation:");
                    System.out.println("\naq: Array-based queue");
                    System.out.println("\nlq: Linked list-based queue");
                    firstTime = false;

                    String choice = scanner.nextLine();
                    while (!choice.equals("aq") && !choice.equals("lq")) {
                        System.out.println("Invalid choice. Please enter 'aq' or 'lq'.");
                        choice = scanner.nextLine();
                    }
                    if (choice.equals("aq")) {
                        queue = new SimpleArrayQueue<>();
                    } else {
                        queue = new SimpleLinkedQueue<>();
                    }
                } else {
                    System.out.println("\n--- Queue State ---");
                    System.out.println("Is empty (isEmpty): " + queue.isEmpty());
                    System.out.println("Size (size): " + queue.size());
                    System.out.println("-------------------");
                }
            }

            switch (currentPhase) {
                case 0:
                    menuLogic();
                    break;
                case 1:
                    doEnqueue();
                    currentPhase = 0;
                    break;
                case 2:
                    doDequeue();
                    currentPhase = 0;
                    break;
                case 3:
                    doPeek();
                    currentPhase = 0;
                    break;
                case 4:
                    doClear();
                    currentPhase = 0;
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    currentPhase = 0;
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
            currentPhase = 0;
        }
    }

    private void menuLogic() {
        System.out.println("\nWhat operation do you want to perform?");
        System.out.println("e: Enqueue");
        System.out.println("d: Dequeue");
        System.out.println("p: Peek");
        System.out.println("c: Clear");
        System.out.println("mm: Main Menu");
        System.out.print("Option: ");

        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "e":
                currentPhase = 1;
                break;
            case "d":
                currentPhase = 2;
                break;
            case "p":
                currentPhase = 3;
                break;
            case "c":
                currentPhase = 4;
                break;
            case "mm":
                currentPhase = 5;
                break;
            default:
                System.out.println("Invalid option.");
                currentPhase = 0;
                break;
        }
    }

    private void doEnqueue() {
        try {
            while (true) {
            System.out.print("Enter the element to enqueue: ");
            String input = scanner.nextLine();
            queue.enqueue(input);
            System.out.println("Element enqueued.");
            System.out.println("\nKeep adding? (y/n): ");

                String remain = scanner.nextLine().toLowerCase();
                while (!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep adding? (y/n): ");
                    remain = scanner.nextLine().toLowerCase();
                }
                if (remain.equals("n")) break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = 0;
    }

    private void doDequeue() {
        while (true) {
            if (queue.isEmpty()) {
                System.out.println("The queue is empty. Cannot Dequeue.");
                break;
            }

            System.out.print("Do you want to dequeue an element? (y/n): ");
            String input = scanner.nextLine().toLowerCase();
            while(!input.equals("n") && !input.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nDo you want to dequeue an element? (y/n): ");
                    input = scanner.nextLine().toLowerCase();
                }
                if(input.equals("n")) break;

            String element = queue.dequeue();
            System.out.println("Dequeued element: " + element);
        }
    }

    private void doPeek() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty. Cannot Peek.");
            return;
        }

        System.out.println("First element (Peek) is: " + queue.peek());
    }

    private void doClear() {
        if (queue.isEmpty()) {
            System.out.println("The queue is already empty.");
        } else {
            queue.clear();
            System.out.println("All elements have been cleared (Clear).");
        }
    }
}