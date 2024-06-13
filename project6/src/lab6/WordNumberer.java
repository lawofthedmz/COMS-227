package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordNumberer
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File file = new File("story.txt");
    Scanner scanner = new Scanner(file);
    int lineCount = 1;
    int wordCount = 0;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      System.out.print(lineCount + " ");
      if(line.length() != 0) {
    	  for(int i = 0; i < line.length(); i++) { 
    		  if(line.charAt(i) == 32) {
    			  wordCount++;
    		  }
    	  }
    	  wordCount++;
    	  
      }
      System.out.println(wordCount);
      wordCount = 0;
      lineCount += 1;
    }
    scanner.close();
  }
}
