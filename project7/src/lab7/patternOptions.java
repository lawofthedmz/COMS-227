package lab7;

public class patternOptions {

	public static void main(String args[]) {
		System.out.println(countPatterns(9));
	}
	
	public static int countPatterns(int n) {
		if(n == 0) {
			return 1;
		}
		if(n <= -1) {
			return 0;
		}
		int howManyWays1 = countPatterns(n-1);
		int howManyWays3 = countPatterns(n-3);
		return howManyWays1 + howManyWays3;
	}
}
