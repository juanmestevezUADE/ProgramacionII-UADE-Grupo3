package setModule;

import application.Exercise;

import java.util.Scanner;

public class SetExercise extends Exercise {
    
    private int currentPhase = 0;
    private boolean firstTime = true;
    SimpleSet<String> setA;
    SimpleSet<String> setB; 
    SimpleSet<String> setChoiced; // Set seleccionado para operaciones individuales
    String setName; // Nombre del set seleccionado (A o B)
    private Scanner scanner;

    public SetExercise(Scanner scnr) {
        super(scnr);
        this.scanner = scnr;
    }

    @Override
    protected void exerciseLogic() {
        try {
            switch (currentPhase) {
                case 0:
                    menuLogic();
                    break;
                case 1:
                    addLogic();
                    break;
                case 2:
                    removeLogic();
                    break;
                case 3:
                    containsLogic();
                    break;
                case 4:
                    unionLogic();
                    break;
                case 5:
                    intersectionLogic();
                    break;
                case 6:
                    differenceLogic(setA, setB); // A - B
                    break;
                case 7:
                    differenceLogic(setB, setA); // B - A
                    break;
                case 10: running = false; break;    
                default:
                    System.out.println("Invalid phase.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during exercise logic.");
        }
    }


    private void menuLogic(){
        try{
            if (currentPhase == 0) {
                if (firstTime) {
                    System.out.println("\nWelcome to the Set exercise!");
                    System.out.println("\nChoose Set implementation:");
                    System.out.println("\nas: Array-based set");
                    System.out.println("\nls: Linked list-based set");
                    firstTime = false;

                    String choice = scanner.nextLine();
                    while (!choice.equals("as") && !choice.equals("ls")) {
                        System.out.println("Invalid choice. Please enter 'as' or 'ls'.");
                        choice = scanner.nextLine();
                    }
                    if (choice.equals("as")) {
                        setA = new SimpleArraySet<>();
                        setB = new SimpleArraySet<>();
                    } else {
                        setA = new SimpleLinkedSet<>();
                        setB = new SimpleLinkedSet<>();
                    }
                } else {
                    System.out.println("\n--- Set A State ---");
                    System.out.println("Is empty (isEmpty): " + setA.isEmpty());
                    System.out.println("Size (size): " + setA.size());
                    System.out.println("-------------------");
                    System.out.println("\n--- Set B State ---");
                    System.out.println("Is empty (isEmpty): " + setB.isEmpty());   
                    System.out.println("Size (size): " + setB.size());
                    System.out.println("-------------------");
                    System.out.println("\nChoose a set:");
                    System.out.println("\na: Set A");
                    System.out.println("\nb: Set B");
                    System.out.println("\nu: Union");
                    System.out.println("\ni: Intersection");
                    System.out.println("\nd: Difference");
                    System.out.println("\nmm: Main Menu");

                    String choice = scanner.nextLine().toLowerCase();
                    switch (choice) {
                        case "a":
                            setChoiced = setA;
                            setName = "A";
                            setOperationLogic(); 
                            break;
                        case "b":  
                            setChoiced = setB;
                            setName = "B";
                            setOperationLogic(); 
                            break;
                        case "u":  currentPhase = 4; break;
                        case "i":  currentPhase = 5; break;
                        case "d":
                            System.out.println("\nChoose difference direction:");
                            System.out.println("\nab: A - B");
                            System.out.println("\nba: B - A");
                            String diffChoice = scanner.nextLine().toLowerCase();
                            while(!diffChoice.equals("ab") && !diffChoice.equals("ba")) {
                                System.out.println("Invalid choice. Please enter 'ab' or 'ba'.");
                                diffChoice = scanner.nextLine().toLowerCase();
                            }
                            if(diffChoice.equals("ab")) {
                                currentPhase = 6; // A - B
                            } else {
                                currentPhase = 7; // B - A
                            }
                            break;
                        case "mm": currentPhase = 10; break;
                        default:
                            System.out.println("Invalid choice. Please enter 'a', 'b', 'u', 'i' or 'd'.");
                    }

                }
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while displaying the menu.");
        }
    }

    private void setOperationLogic() {
        try {
            System.out.println("\nChoose an operation:");
            System.out.println("\na: Add an element");
            System.out.println("\nr: Remove an element");
            System.out.println("\nc: Check if an element exists");
            System.out.println("\nmm: Main Menu");

            String choice = scanner.nextLine().toLowerCase();
            while (!choice.equals("a") && !choice.equals("r") && !choice.equals("c") && !choice.equals("mm")) {
                System.out.println("Invalid choice. Please enter 'a', 'r', 'c' or 'mm'.");
                choice = scanner.nextLine().toLowerCase();
            }
            switch (choice) {
                case "a":  currentPhase = 1; break;
                case "r":  currentPhase = 2; break;
                case "c":  currentPhase = 3; break;
                case "mm": currentPhase = 10; break;
                default: break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while performing the set operation.");
        }
    }

    private void addLogic() {
        try {
            while (true) {
                System.out.println("\nEnter an element to add:");
                String element = scanner.nextLine();
                if (setChoiced.add(element)) {
                    System.out.println("Element added successfully.");
                } else {
                    System.out.println("Element already exists in the set.");
                }
                System.out.println("\nKeep adding? (y/n): ");
                String remain = scanner.nextLine().toLowerCase();
                while (!remain.equals("y") && !remain.equals("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    remain = scanner.nextLine().toLowerCase();
                }
                if (remain.equals("n")) break;
            }
            if (setName.equals("A")) {
                setA = setChoiced; // Volver al menú principal
            }else{
                setB = setChoiced; // Volver al menú principal
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding an element.");
        }
        currentPhase = 0;
    }

    private void removeLogic() {
        // Similar structure to addLogic, but for removing elements
        try{
            while (true) {
                System.out.println("\nEnter an element to remove:");
                String element = scanner.nextLine();
                if (setChoiced.remove(element)) {
                    System.out.println("Element removed successfully.");
                } else {
                    System.out.println("Element not found in the set.");
                }
                System.out.println("\nKeep removing? (y/n): ");
                String remain = scanner.nextLine().toLowerCase();
                while (!remain.equals("y") && !remain.equals("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    remain = scanner.nextLine().toLowerCase();
                }
                if (remain.equals("n")) break;
            }
            if (setName.equals("A")) {
                setA = setChoiced; // Volver al menú principal
            }else{
                setB = setChoiced; // Volver al menú principal
            }
        } catch (Exception e) {
            System.out.println("An error occurred while removing an element.");
        }
        currentPhase = 0;
    }

    private void containsLogic() {
        // Similar structure to addLogic, but for checking if an element exists
        try{
            while (true) {
                System.out.println("\nEnter an element to check:");
                String element = scanner.nextLine();
                if (setChoiced.contains(element)) {
                    System.out.println("Element exists in the set.");
                } else {
                    System.out.println("Element does not exist in the set.");
                }
                System.out.println("\nKeep checking? (y/n): ");
                String remain = scanner.nextLine().toLowerCase();
                while (!remain.equals("y") && !remain.equals("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    remain = scanner.nextLine().toLowerCase();
                }
                if (remain.equals("n")) break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking for an element.");
        }
        currentPhase = 0;
    }

    private void unionLogic() {
        try {
            SimpleSet<String> unionSet = setA.unionWith(setB);
            System.out.println("\nUnion of Set A and Set B:");
            String unionSetPrint = "";
            Object [] unionSetarray = unionSet.toArray();
            for (int i = 0; i < unionSet.size(); i++) {
                unionSetPrint += unionSetarray[i];
                if(i < unionSet.size() - 1) unionSetPrint += ", ";
            }
            System.out.println(unionSetPrint);
        } catch (Exception e) {
            System.out.println("Union operation could not be performed: " + e.getMessage());
            // Si toArray no está implementado, no podemos mostrar la unión
        }
        currentPhase = 0;
    }

    private void intersectionLogic() {
        try {
            SimpleSet<String> intersectionSet = setA.intersectionWith(setB);
            System.out.println("\nIntersection of Set A and Set B:");
            String intersectionSetPrint = "";
            Object [] intersectionSetarray = intersectionSet.toArray();
            for (int i = 0; i < intersectionSet.size(); i++) {
                intersectionSetPrint += intersectionSetarray[i];
                if(i < intersectionSet.size() - 1) intersectionSetPrint += ", ";
            }
            System.out.println(intersectionSetPrint);
        } catch (Exception e) {
            System.out.println("Intersection operation could not be performed");
            // Si toArray no está implementado, no podemos mostrar la intersección
            
        }
        currentPhase = 0;
    }

    private void differenceLogic(SimpleSet<String> set1, SimpleSet<String> set2) {
        try {
            SimpleSet<String> differenceSet = set1.differenceWith(set2);
            System.out.println("\nDifference of Set " + (set1 == setA ? "A" : "B") + " - Set " + (set2 == setA ? "A" : "B") + ":");
            String differenceSetPrint = "";
            Object [] differenceSetarray = differenceSet.toArray();
            for (int i = 0; i < differenceSet.size(); i++) {
                differenceSetPrint += differenceSetarray[i];
                if(i < differenceSet.size() - 1) differenceSetPrint += ", ";
            }
            System.out.println(differenceSetPrint);
        } catch (Exception e) {
            System.out.println("Difference operation could not be performed");
        }
        currentPhase = 0;
    }
}
