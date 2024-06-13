package lab7;

public class pyramidCount {
	public static void main(String args[]) {
		System.out.println(getPyramidCount(7));
	}
	
	public static int getPyramidCount(int levels) {
		if(levels == 1) {
			return levels * levels;
		}
		else {
		return (levels * levels) + getPyramidCount(levels - 1);
		}
	}
}
