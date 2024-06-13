package lab2;

import java.util.Random;

public class StringTest {
	

	public static void main(String[] args) {
		String message = new String("Murphy is awesome");
		char theChar = message.charAt(0);
		System.out.println(theChar);

		theChar = message.charAt(2);
		System.out.println(theChar);
		
		String upperCaseMessage = message.toUpperCase(); // makes message uppercase assigning to new variable
		System.out.println(upperCaseMessage);
		
		String subMessage = message.substring(8); // assigns new variable with every char after index 8 
		System.out.println(subMessage);
		
		Random rand = new Random();
		
	}
	

}
