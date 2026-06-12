package application;

import java.util.Scanner;

import ListModule.ListExercise;
import stackModule.SimpleStackExercise;
import QueueModule.QueueExercise;
import setModule.SetExercise;
import priorityqueueModule.PriorityqueueExercise;
import dictionaryModule.DictionaryExercise;
import treeModule.BSTExercise;

//Esto importa el paquete para leer datos del teclado 

public class MainProgram {
	public boolean running = true;
	public int loginTries = 0;
	public dictionaryModule.SimpleDictionary<String, String> loginDictionary = null;
	public dictionaryModule.SimpleDictionary<String, String> blockedAccounts = null;

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
				"\n4: Queue Exercise" +
				"\n5: Set Exercise" +
				"\n6: Priority Queue Exercise" +
				"\n7: Dictionary Exercise" +
				"\n8: BST Exercise");
		
		
		
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
		case "5":
			exercise = new SetExercise(scanner);
			break;
		case "6":
			exercise = new PriorityqueueExercise(scanner);
			break;
		case "7":
			exercise = new DictionaryExercise(scanner, this);
			break;
		case "8":
			exercise = new BSTExercise(scanner);
			break;
		default:
			System.out.println("\n Invalid input, try again");
			selectExercise(scanner);
			break;
		}
	}
}
