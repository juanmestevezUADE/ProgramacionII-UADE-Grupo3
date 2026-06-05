package priorityqueueModule;

import java.util.Scanner;

import application.Exercise;

public class PriorityqueueExercise extends Exercise{

    public PriorityqueueExercise(Scanner scnr) {
        super(scnr);
        //TODO Auto-generated constructor stub
    }

    private SimplePriorityqueue<String> priorityQueue;
    private int currentPhase = 0;
    private boolean firstTime = true;
    private Scanner scanner;

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
        
            default:
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
                priorityQueue = new SimpleArrayPriorityqueue<>();
            } else {
                priorityQueue = new SimpleLinkedPriorityqueue<>();
            }
        } else {
            System.out.println("\n--- System consumer attention ---");
            System.out.println("Is empty (isEmpty): " + priorityQueue.isEmpty());
            System.out.println("Size (size): " + priorityQueue.size());
            System.out.println("What do you want to do?" +
                    "\nr: Register a claim" +
                    "\nv: View claims" +
                    "\nq: Quit");
        }
    }
}
