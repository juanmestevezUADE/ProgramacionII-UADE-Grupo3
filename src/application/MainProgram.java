package application;

import java.util.Scanner;

import ListModule.ListExercise;
import stackModule.SimpleStackExercise;
import QueueModule.QueueExercise;
import setModule.SetExercise;
import priorityqueueModule.PriorityqueueExercise;
import dictionaryModule.DictionaryExercise;
import treeModule.BSTExercise;
import treeModule.AVLExercise;
import graphModule.GraphExercise;

// Punto de entrada del programa. Muestra el menú principal y delega la ejecución
// al ejercicio correspondiente según la opción que elija el usuario.
public class MainProgram {

    public boolean running = true;
    public int loginTries = 0;

    // Diccionarios compartidos del ejercicio de Dictionary, para que persistan
    // entre sesiones del mismo ejercicio sin perder los datos
    public dictionaryModule.SimpleDictionary<String, String> loginDictionary = null;
    public dictionaryModule.SimpleDictionary<String, String> blockedAccounts = null;

    private Exercise exercise;

    public static void main(String[] args) {
        new MainProgram().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            selectExercise(scanner);
            if (exercise != null) exercise.run();
        }

        scanner.close();
        System.out.println("Program terminated");
    }

    // Muestra el menú de selección y crea el ejercicio correspondiente
    private void selectExercise(Scanner scanner) {
        System.out.println("\nSelect an option:" +
                "\n0: Terminate Program" +
                "\n1: Test Exercise" +
                "\n2: List Exercise" +
                "\n3: Stack Exercise" +
                "\n4: Queue Exercise" +
                "\n5: Set Exercise" +
                "\n6: Priority Queue Exercise" +
                "\n7: Dictionary Exercise" +
                "\n8: BST Exercise" +
                "\n9: AVL Exercise" +
                "\n10: GPS Exercise");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "0":
                running = false;
                break;
            case "1":
                exercise = new TestExercise(scanner);
                break;
            case "2":
                exercise = new ListExercise(scanner);
                break;
            case "3":
                exercise = new SimpleStackExercise(scanner);
                break;
            case "4":
                exercise = new QueueExercise(scanner);
                break;
            case "5":
                exercise = new SetExercise(scanner);
                break;
            case "6":
                exercise = new PriorityqueueExercise(scanner);
                break;
            case "7":
                // Pasamos 'this' para que DictionaryExercise pueda acceder al estado compartido
                exercise = new DictionaryExercise(scanner, this);
                break;
            case "8":
                exercise = new BSTExercise(scanner);
                break;
            case "9":
                exercise = new AVLExercise(scanner);
                break;
            case "10":
                exercise = new GraphExercise(scanner);
                break;
            default:
                System.out.println("\n Invalid input, try again");
                selectExercise(scanner);
                break;
        }
    }
}
