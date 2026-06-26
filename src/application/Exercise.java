package application;

import java.util.Scanner;

// Clase base abstracta para todos los ejercicios del programa.
// Cada ejercicio hereda de esta clase y define su propia lógica en exerciseLogic().
// El método run() ejecuta el ejercicio en un loop hasta que running se pone en false.
public abstract class Exercise {

    protected boolean running = true; // controla si el ejercicio sigue corriendo
    protected Scanner scanner;        // scanner compartido con el programa principal

    public Exercise(Scanner scnr) {
        scanner = scnr;
    }

    // Ejecuta el ejercicio en loop hasta que el usuario decide salir
    public void run() {
        while (running) exerciseLogic();
    }

    // Cada ejercicio implementa aquí su lógica particular (menú, operaciones, etc.)
    protected abstract void exerciseLogic();
}
