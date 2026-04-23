package application;

import java.util.Scanner;

public class TestExercise extends Exercise {

	public TestExercise(Scanner scnr) { super(scnr); }
	@Override
	protected void exerciseLogic() {
		System.out.println("\nWelcome to the test exercise." +
				 "\nmm: Main menu"
				);
		
		String userInput = scanner.nextLine().toLowerCase();

		if(userInput.equals("mm")) running = false;
		else System.out.println("\nInvalid input, try again.");
	}

}
