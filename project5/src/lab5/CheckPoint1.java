package lab5;


public class CheckPoint1 {

	public static String getInitials(String fullName) {
		String initials = "";
		String[] parts = fullName.split(" ");
		
		for (String singlePart : parts) {
			initials += singlePart.charAt(0);
			
		}
		
		return initials;
		
	}
	
	public static int getVowels(String str) {
		
		for (int i = 0; i < str.length(); i++) {
			if ("aeiouAEIOU".indexOf(str.charAt(i)) >= 0) {
				return i;
			}
		}
		
		return -1;
	}
	public static void main(String[] args) {
		
		System.out.println("Your initials: " + getInitials("Murphy Reagan Glawe"));
		
		System.out.println("First vowel index: " + getVowels("shrimp"));
		System.out.println("First vowel index: " + getVowels("Why"));

	}

}
