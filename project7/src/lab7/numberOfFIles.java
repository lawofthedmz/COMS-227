package lab7;

import java.io.File;

public class numberOfFIles {
	public static void main(String[] args)
	  {
	    File f = new File(".");
	    int numOfFiles = countFiles(f);
	    System.out.println("Num of files: " + numOfFiles);
	   /* File f = new File("../project6");
	    System.out.println("Does it exist? " + f.exists());
	    System.out.println("Is this a directory? " + f.isDirectory());
	    System.out.println();
	    
	    // list the immediate contents of the project6 directory
	    File[] files = f.listFiles();
	    for (int i = 0; i < files.length; ++i)
	    {
	      System.out.println(files[i].getName());
	    }
	    */
	  }
	
	public static int countFiles(File f) {
		
		if(f.isFile()) {
			return 1;
		}
		else {
			int count = 0;
			File[] files = f.listFiles();
		      for (int i = 0; i < files.length; ++i)
		      {
		        count += countFiles(files[i]);
		      }
		      return count;
			
		}
	}
}
