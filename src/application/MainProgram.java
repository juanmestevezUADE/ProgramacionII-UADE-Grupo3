package application;

import java.util.Scanner;

import ListModule.ListExercise;
import stackModule.SimpleStackExercise;
import QueueModule.QueueExercise;
//Esto importa el paquete para leer datos del teclado 

public class MainProgram {
	public boolean running = true;
	
	private Exercise exercise;
	
	public static void main(String[] args) {
		new MainProgram().run();

	}
	
	private void run()
	{
		Scanner scanner = new Scanner(System.in);
		
		
		//podemos omitir el == true con los booleanos
		while(running)
		{
			selectExercise(scanner);
			if(exercise != null) exercise.run();
			}
		
		scanner.close();
		System.out.println("Program terminated");
	}
	
	private void selectExercise(Scanner scanner)
	{
		System.out.println("\nSelect an option:" +
				"\n0: Terminate Program" +
				"\n1: Test Exercise" +
				"\n2: List Exercise" +
				"\n3: Stack Exercise" + 
				"\n4: Queue Exercise");
		
		
		
		String userInput = scanner.nextLine();
		
		switch(userInput)
		{
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
		default:
			System.out.println("\n Invalid input, try again");
			selectExercise(scanner);
			break;
		}
		
	}

}
