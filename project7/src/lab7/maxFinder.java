package lab7;

public class maxFinder {
	public static void main(String args[]) {
		int[] list = {3, 4, 7, 1, 2, 9, 2};
		System.out.println(arraySum(list));
	}
	
	 public static int arraySum(int[] arr)
	  {
	    return maxFind(arr, 0, arr.length - 1);
	  }
	
	public  static int maxFind(int[] list, int start,int end) {
		int result;
		if(start== end ){
			return list[start];
		}
		else {
		 int mid = (start + end) / 2;
	     int leftMax = maxFind(list, start, mid);
	     int rightMax = maxFind(list, mid + 1, end);
		
	    if(leftMax > rightMax) {
	    	result = leftMax;
	    }
	    else {
	    	result = rightMax;
	    }
		return result;
		}
	}
}
