package dictionaryModule;

import java.util.Scanner;

import application.Exercise;
import application.MainProgram;

public class DictionaryExercise extends Exercise{

    private MainProgram mainProgram;

    public DictionaryExercise(Scanner scnr, MainProgram main) {
        super(scnr);
        this.mainProgram = main;

        // Si ya existe un diccionario de sesiones anteriores, reutilizarlo
        if (main.loginDictionary != null) {
            login = main.loginDictionary;
            blockedAccounts = main.blockedAccounts;
            firstTime = false;
        }
    }

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleDictionary<String, String> login;
    private SimpleDictionary<String, String> blockedAccounts;

    @Override
    protected void exerciseLogic() {
        switch(currentPhase) {
        case 0:  menuLogic();     break;
        case 9:  running = false; break;
        }
    }

    private void menuLogic() {
        if(firstTime) {
            System.out.println("\nWelcome to the Dictionary exercise!");
            System.out.println("\nChoose Dictionary implementation:");
            System.out.println("\nad: Array-based Dictionary");
            System.out.println("\nld: Linked list-based Dictionary");
            firstTime = false;

            String choice = scanner.nextLine();
            while (!choice.equals("ad") && !choice.equals("ld")) {
                System.out.println("Invalid choice. Please enter 'ad' or 'ld'.");
                choice = scanner.nextLine();
            }
            if (choice.equals("ad")) {
                login = new SimpleArrayDictionary<>();
                blockedAccounts = new SimpleArrayDictionary<>();
            } else {
                login = new SimpleLinkedDictionary<>();
                blockedAccounts = new SimpleLinkedDictionary<>();
            }
            // Guardar en MainProgram para que persistan al volver al menu
            mainProgram.loginDictionary = login;
            mainProgram.blockedAccounts = blockedAccounts;

        } else {
            System.out.println("Please select an option:");
            System.out.println("l: Login");
            System.out.println("r: Register");
            System.out.println("q: Quit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "l":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    if (blockedAccounts.containsKey(username)) {
                        System.out.println("This account is blocked.");
                        break;
                    }
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    String storedPassword = login.get(username);
                    if (storedPassword != null && storedPassword.equals(password)) {
                        System.out.println("Login successful!");
                        mainProgram.loginTries = 0;
                        userInterface(username);
                    } else {
                        System.out.println("Invalid username or password.");
                        mainProgram.loginTries++;
                        if (mainProgram.loginTries >= 3) {
                            System.out.println("Too many failed attempts. Account '" + username + "' is now blocked.");
                            blockedAccounts.put(username, "blocked");
                            mainProgram.loginTries = 0;
                        }
                    }
                    break;
                case "r":
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    if (login.containsKey(newUsername)) {
                        System.out.println("Username already exists. Please choose a different username.");
                    } else {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        login.put(newUsername, newPassword);
                        System.out.println("Registration successful! You can now log in.");
                    }
                    break;
                case "q":
                    System.out.println("Exiting the exercise. Goodbye!");
                    currentPhase = 9;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void userInterface(String username) {
        boolean runningInterface = true;
        while (runningInterface) {
            System.out.println("\nWelcome to the user interface!");
            System.out.println("Here you can manage your account.");
            System.out.println("Options:");
            System.out.println("c: Change password");
            System.out.println("d: Delete account");
            System.out.println("q: Logout");
            String choice = scanner.nextLine();
            switch (choice) {
                case "c":
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    login.put(username, newPassword);
                    System.out.println("Password changed successfully!");
                    break;
                case "d":
                    login.remove(username);
                    System.out.println("Account deleted successfully!");
                    runningInterface = false;
                    break;
                case "q":
                    System.out.println("Logged out successfully!");
                    runningInterface = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
