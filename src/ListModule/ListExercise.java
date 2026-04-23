package ListModule;

import java.util.Scanner;
import application.Exercise;

public class ListExercise extends Exercise {

    public ListExercise(Scanner scnr) {
        super(scnr);
    }

    @Override
    protected void exerciseLogic() {
        switch(currentPhase) {
        case 0:  menuLogic();         break;
        case 1:  addLogic();          break;
        case 2:  addByIndexLogic();   break;
        case 3:  removeByIndexLogic();break;
        case 4:  removeByRefLogic();  break;
        case 5:  clearLogic();        break;
        case 6:  containsLogic();     break;
        case 7:  getLogic();          break;
        case 8:  setLogic();          break;
        case 9:  running = false;     break;
        }
    }

    private int currentPhase = 0;
    private boolean firstTime = true;
    SImpleList<String> list;

    private void menuLogic() {
        if(firstTime) {
            System.out.println("\nWelcome to the List exercise"
                    + "\nWhich implementation do you prefer?"
                    + "\nal: Array List"
                    + "\nll: Linked List");

            String choice = scanner.nextLine();

            while(!choice.equals("al") && !choice.equals("ll")) {
                System.out.println("\nPlease enter a valid value.");
                choice = scanner.nextLine();
            }

            if(choice.equals("al")) {
                list = new SimpleArrayList<>();
            } else {
                list = new SimpleLinkedList<>();
            }

            firstTime = false;
        } else {
            String fullList = "";
            for(int i = 0; i < list.size(); i++) {
                fullList += list.get(i);
                if(i < list.size() - 1) fullList += ", ";
            }
            System.out.println("\nList contents: " + fullList
                    + "\nList size: " + list.size()
                    + "\nList is empty: " + list.isEmpty());
        }

        System.out.println("\nChoose an option:"
                + "\nadd: Add element at end"
                + "\nadd ind: Add element by index"
                + "\nrem ind: Remove by index"
                + "\nrem ref: Remove by reference"
                + "\nclear: Clear list"
                + "\ncontains: Check if element exists"
                + "\nget: Get element by index"
                + "\nset: Replace element by index"
                + "\nmm: Main menu");

        String userInput = scanner.nextLine();

        switch(userInput) {
        case "add":      currentPhase = 1; break;
        case "add ind":  currentPhase = 2; break;
        case "rem ind":  currentPhase = 3; break;
        case "rem ref":  currentPhase = 4; break;
        case "clear":    currentPhase = 5; break;
        case "contains": currentPhase = 6; break;
        case "get":      currentPhase = 7; break;
        case "set":      currentPhase = 8; break;
        case "mm":       currentPhase = 9; break;
        default:
            System.out.println("Invalid input, try again.");
            break;
        }
    }

    private void addLogic() {
        try {
            while(running) {
                System.out.println("\nEnter element: ");
                String newElement = scanner.nextLine();
                list.add(newElement);

                String fullList = "";
                for(int j = 0; j < list.size(); j++) {
                    fullList += list.get(j);
                    if(j < list.size() - 1) fullList += ", ";
                }
                System.out.println("\nList contents: " + fullList
                        + "\nKeep adding? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep adding? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(Exception e) {
            System.out.println("\nAn error occurred while adding.");
        }
        running = true;
        currentPhase = 0;
    }

    private void addByIndexLogic() {
        try {
            if(list.isEmpty()) {
                System.out.println("\nList is empty. Use add first.");
                currentPhase = 0;
                return;
            }
            while(running) {
                System.out.println("\nEnter the index where you want to add the element: ");
                int index = Integer.parseInt(scanner.nextLine());

                System.out.println("\nEnter the element: ");
                String newElement = scanner.nextLine();
                list.add(index, newElement);

                String fullList = "";
                for(int j = 0; j < list.size(); j++) {
                    fullList += list.get(j);
                    if(j < list.size() - 1) fullList += ", ";
                }
                System.out.println("\nList contents: " + fullList
                        + "\nKeep adding? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep adding? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\nIndex out of range.");
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        running = true;
        currentPhase = 0;
    }

    private void removeByIndexLogic() {
        try {
            while(running) {
                System.out.println("\nEnter the index of the element to remove: ");
                int index = Integer.parseInt(scanner.nextLine());
                String removed = list.remove(index);
                System.out.println("\nRemoved: " + removed);

                String fullList = "";
                for(int j = 0; j < list.size(); j++) {
                    fullList += list.get(j);
                    if(j < list.size() - 1) fullList += ", ";
                }
                System.out.println("\nList contents: " + fullList
                        + "\nKeep removing? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep removing? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\nIndex out of range.");
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        running = true;
        currentPhase = 0;
    }

    private void removeByRefLogic() {
        try {
            while(running) {
                System.out.println("\nEnter the element to remove: ");
                String element = scanner.nextLine();
                boolean ok = list.remove(element);

                if(ok) {
                    System.out.println("\nElement removed.");
                } else {
                    System.out.println("\nElement not found.");
                }

                String fullList = "";
                for(int j = 0; j < list.size(); j++) {
                    fullList += list.get(j);
                    if(j < list.size() - 1) fullList += ", ";
                }
                System.out.println("\nList contents: " + fullList
                        + "\nKeep removing? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep removing? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        running = true;
        currentPhase = 0;
    }

    private void clearLogic() {
        try {
            System.out.println("\nAre you sure you want to clear the list? (y/n): ");
            String choice = scanner.nextLine();

            while(!choice.equals("n") && !choice.equals("y")) {
                System.out.println("\nPlease enter a valid value.");
                System.out.println("\nAre you sure you want to clear the list? (y/n): ");
                choice = scanner.nextLine();
            }

            if(choice.equals("y")) {
                list.clear();
                System.out.println("\nList cleared.");
            }
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        currentPhase = 0;
    }

    private void containsLogic() {
        try {
            System.out.println("\nEnter the element to search: ");
            String element = scanner.nextLine();
            boolean found = list.contains(element);
            System.out.println("\nContains \"" + element + "\": " + found);
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        currentPhase = 0;
    }

    private void getLogic() {
        try {
            while(running) {
                System.out.println("\nEnter the index of the element to get: ");
                int index = Integer.parseInt(scanner.nextLine());
                String element = list.get(index);
                System.out.println("\nElement at index " + index + ": " + element);
                System.out.println("\nKeep searching? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep searching? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\nIndex out of range.");
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        running = true;
        currentPhase = 0;
    }

    private void setLogic() {
        try {
            while(running) {
                System.out.println("\nEnter the index of the element to replace: ");
                int index = Integer.parseInt(scanner.nextLine());

                System.out.println("\nEnter the new value: ");
                String newElement = scanner.nextLine();
                String oldElement = list.set(index, newElement);

                System.out.println("\nPrevious element: " + oldElement);
                System.out.println("New element: " + newElement);

                String fullList = "";
                for(int j = 0; j < list.size(); j++) {
                    fullList += list.get(j);
                    if(j < list.size() - 1) fullList += ", ";
                }
                System.out.println("\nList contents: " + fullList
                        + "\nKeep replacing? (y/n): ");

                String remain = scanner.nextLine();
                while(!remain.equals("n") && !remain.equals("y")) {
                    System.out.println("\nPlease enter a valid value.");
                    System.out.println("\nKeep replacing? (y/n): ");
                    remain = scanner.nextLine();
                }
                if(remain.equals("n")) running = false;
            }
        } catch(NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\nIndex out of range.");
        } catch(Exception e) {
            System.out.println("\nAn unexpected error occurred.");
        }
        running = true;
        currentPhase = 0;
    }
}