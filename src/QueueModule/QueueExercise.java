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
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Peek");
        System.out.println("4. Clear");
        System.out.println("5. Exit");
        System.out.print("Option: ");

        String input = scanner.nextLine();
        try {
            currentPhase = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            currentPhase = -1;
        }
    }

    private void doEnqueue() {
        while (true) {
            System.out.print("Enter the element to enqueue (or leave empty to go back): ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                break;
            }
            queue.enqueue(input);
            System.out.println("Element enqueued.");
        }
    }

    private void doDequeue() {
        while (true) {
            if (queue.isEmpty()) {
                System.out.println("The queue is empty. Cannot Dequeue.");
                break;
            }

            System.out.print("Do you want to dequeue an element? (y/n): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                break;
            }

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