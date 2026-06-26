package treeModule;

import application.Exercise;
import ListModule.SImpleList;


import java.util.Scanner; 

// Ejercicio de scoreboard usando un BST de ScoreEntry.
// Al iniciar carga datos de prueba. Permite insertar nuevas entradas y mostrarlas en orden.
// El BST ordena las entradas por puntaje (mayor primero) gracias al compareTo de ScoreEntry.
public class BSTExercise extends Exercise{

    public BSTExercise(Scanner scnr) {
        super(scnr);
        this.scanner = scnr;
        //TODO Auto-generated constructor stub
    }

    private Scanner scanner;
    private int currentPhase = 0;
    private boolean firstTime = true;
    BST<ScoreEntry> bst = new BST<>();


    @Override
    protected void exerciseLogic() {
        if(firstTime) {
            loadSampleData();
            firstTime = false;
        }
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                doInsert();
                currentPhase = 0;
                break;
            case 2:
                doInOrder();
                currentPhase = 0;
                break;
            case 3:
                System.out.println("Exiting exercise. Returning to main menu.");
                running = false; // Detener el ejercicio para volver al menú principal
                break;
            default:
                break;
        }
    }

    private void menuLogic() {
        System.out.println("\nWelcome to the ScoreBoard exercise!");
        System.out.println("\nChoose an option:");
        System.out.println("\n1: Insert your score entry");
        System.out.println("\n2: Display all score entries in order");
        System.out.println("\n3: Exit");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                currentPhase = 1;
                break;
            case "2":
                currentPhase = 2;
                break;
            case "3":
                currentPhase = 3;
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                break;
        }
    }

    private void doInsert() {
        try{
            System.out.println("\nEnter your name:");
            String name = scanner.nextLine();
            if(name == null || name.trim().isEmpty()) {
                throw new NullPointerException("Name cannot be empty");
            }
            System.out.println("Enter the number of enemies defeated:");
            int enemies = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the time taken (in seconds):");
            int time = Integer.parseInt(scanner.nextLine());
            ScoreEntry entry = new ScoreEntry(name, enemies, time);
            bst.insert(entry);
            System.out.println("Score entry inserted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for enemies and time.");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void doInOrder() {
        System.out.println("\n--- Scoreboard (In-Order) ---");
        SImpleList<ScoreEntry> entries = bst.inOrder();
        for(int i = 0; i < entries.size(); i++) {
            ScoreEntry entry = entries.get(i);
            System.out.println("Name: " + entry.name + ", Score: " + entry.score);
        }
        System.out.println("-----------------------------");
    }

    private void loadSampleData() {
    bst.insert(new ScoreEntry("Alice", 15, 30));
    bst.insert(new ScoreEntry("Bob", 10, 20));
    bst.insert(new ScoreEntry("Charlie", 20, 60));
    bst.insert(new ScoreEntry("Diana", 15, 30)); // mismo puntaje que Alice, distinto jugador
    bst.insert(new ScoreEntry("Eve", 5, 10));
    System.out.println("Sample data loaded successfully!");
}
}
