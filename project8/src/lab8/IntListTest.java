package lab8;

/**
 * A class to test the IntListSorted class.
 */
public class IntListTest {
  
  public static void main(String[] args) {
    
    IntListSorted list = new IntListSorted();
    
    list.add(1);
    list.add(10);
    list.add(0);
    list.add(3);
    System.out.println(list);
    System.out.println("Size: " + list.size());
    System.out.println("Min: " + list.getMinimum());
    System.out.println("Max: " + list.getMaximum());
    System.out.println("Median: " + list.getMedian());
  }
}
