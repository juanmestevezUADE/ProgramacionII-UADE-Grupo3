package priorityqueueModule;

import java.util.Scanner;

import application.Exercise;

public class PriorityqueueExercise extends Exercise{

    public PriorityqueueExercise(Scanner scnr) {
        super(scnr);
        this.scanner = scnr;
        //TODO Auto-generated constructor stub
    }

    private SimplePriorityqueue<String> titlePriorityQueue;
    private SimplePriorityqueue<String> descriptionPriorityQueue;
    private int currentPhase = 0;
    private boolean firstTime = true;
    private Scanner scanner;

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                registerClaim();
                break;
            case 2:
                viewClaims();
                break;
            case 3:
                System.out.println("Exiting Priority Queue exercise.");
                running = false;
                break;
            default:
                System.out.println("Invalid phase. Resetting to menu.");
                currentPhase = 0;
                break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("\nWelcome to the Priority Queue exercise!");
            System.out.println("\nChoose priority queue implementation:");
            System.out.println("\naq: Array-based priority queue");
            System.out.println("\nlq: Linked list-based priority queue");
            firstTime = false;

            String choice = scanner.nextLine();
            while (!choice.equals("aq") && !choice.equals("lq")) {
                System.out.println("Invalid choice. Please enter 'aq' or 'lq'.");
                choice = scanner.nextLine();
            }
            if (choice.equals("aq")) {
                titlePriorityQueue = new SimpleArrayPriorityqueue<>();
                descriptionPriorityQueue = new SimpleArrayPriorityqueue<>();

            } else {
                titlePriorityQueue = new SimpleLinkedPriorityqueue<>();
                descriptionPriorityQueue = new SimpleLinkedPriorityqueue<>();
            }
        } else {
            System.out.println("\n--- System consumer attention ---");
            System.out.println("Is empty (isEmpty): " + titlePriorityQueue.isEmpty());
            System.out.println("Size (size): " + titlePriorityQueue.size());
            System.out.println("What do you want to do?" +
                    "\nr: Register a claim" +
                    "\nv: View claims" +
                    "\nq: Quit");
            
            String action = scanner.nextLine();
            switch (action) {
                case "r":
                    currentPhase = 1;
                    break;
                case "v":
                    currentPhase = 2;
                    break;
                case "q":
                    currentPhase = 3;
                    break;
                default:
                    System.out.println("Invalid action. Please enter 'r', 'v', or 'q'.");
                    break;
            }
        }
    }

    private int choosePriority() {
        System.out.println("Choose priority level:" +
                "\n1: Low" +
                "\n2: Medium" +
                "\n3: High" +
                "\n4: Critical");
        String priorityInput = scanner.nextLine();
        while (!priorityInput.equals("1") && !priorityInput.equals("2") && !priorityInput.equals("3") && !priorityInput.equals("4")) {
            System.out.println("Invalid priority. Please enter '1', '2', '3' or '4'.");
            priorityInput = scanner.nextLine();
        }
        switch (priorityInput) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
        }
        return 1; // Default to low priority if something goes wrong
    }

    private String priorityToString(int priority) {
        switch (priority) {
            case 1:
                return "Low";
            case 2:
                return "Medium";
            case 3:
                return "High";
            case 4:
                return "Critical";
            default:
                return "Unknown";
        }
    }

    private String titleOrDescriptionValidation(String input) {
        while (input.trim().isEmpty()) {
            System.out.println("Input cannot be empty. Please enter a valid value:");
            input = scanner.nextLine();
        }
        return input;
    }

    private void registerClaim() {
        System.out.println("Enter claim title:");
        String title = scanner.nextLine();
        title = titleOrDescriptionValidation(title);
        int priority = choosePriority();
        titlePriorityQueue.enqueue(title, priority);

        System.out.println("Enter claim description:");
        String description = scanner.nextLine();
        description = titleOrDescriptionValidation(description);
        descriptionPriorityQueue.enqueue(description, priority);
        String priorityStr = priorityToString(priority);

        System.out.println("Claim registered successfully with " + priorityStr + " priority.");
        currentPhase = 0;
    }

    private void viewClaims() {
        if (titlePriorityQueue.isEmpty()) {
            System.out.println("No claims to display.");
        } else {
            System.out.println("\n--- Claims in Priority Order ---");
            while (!titlePriorityQueue.isEmpty()) {
                String title = titlePriorityQueue.peek();
                String description = descriptionPriorityQueue.peek();
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("-----------------------------");
                System.out.println("Do you want to mark this claim as solved? (y/n)");
                String choice = scanner.nextLine().toLowerCase();
                while (!choice.equals("y") && !choice.equals("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'");
                    choice = scanner.nextLine();
                }
                if(choice.equals("y")) {
                    titlePriorityQueue.dequeue();
                    descriptionPriorityQueue.dequeue();
                    System.out.println("Claim marked as solved and removed from the queue.");
                } else {
                    System.out.println("Claim remains in the queue.");
                    System.out.println("Exiting claim view.");
                    break;
                }
            }
        }
        currentPhase = 0;
    }
}
