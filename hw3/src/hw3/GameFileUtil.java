package hw3;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import api.BodySegment;	
import api.Cell;
import api.Exit;
import api.Wall;



/**
 * Utility class with static methods for loading game files.
 */
public class GameFileUtil {
	/**
	 * Keeps track of what row of text file program is on 
	 */
	private static int lineRow = 0; 
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, LizardGame game) {
		// Open the file and read its contents
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, game, lineRow);
                lineRow++;
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Error reading file: " + e.getMessage());
        }
	}
	
	/**
     * Processes a line from the game file and modifies the game object accordingly.
     * 
     * @param line the line from the game file
     * @param game the game object to modify
     */
	private static void processLine(String line, LizardGame game, int lineRow) {
	 	
	    if (line.contains("x")) {
	        // Extract grid dimensions from the line
	        String[] dimensions = line.split("x");
	        int width = Integer.parseInt(dimensions[0].trim());
	        int height = Integer.parseInt(dimensions[1].trim());
	        // Reset the grid dimensions in the game object
	        game.resetGrid(width, height);
	    } else if (line.startsWith("L")) {
	        // Process lizards
	        String[] segments = line.substring(2).split(" ");
	        Lizard newLizard = new Lizard();
	        // Iterate over segments and create BodySegment objects
	        for (String segment : segments) {
	            String[] coordinates = segment.split(",");
	            int col = Integer.parseInt(coordinates[0]);
	            int row = Integer.parseInt(coordinates[1]);
	            // Create BodySegment objects directly within the Lizard constructor
	            Cell cell = game.getCell(col, row);
	            if (cell != null) {
	                BodySegment segmentObj = new BodySegment(newLizard, cell);
	                newLizard.getSegments().add(segmentObj);
	            }
	        }
	        // Add lizard to the game object
	        game.addLizard(newLizard);
	    } else {
	    	for (int i = 0; i < line.length(); i++) {
	    		if (line.charAt(i) == 'W') { // If program is on W
	    			int col = i;
		            int row = lineRow - 1;
		            // Add wall to the game object
		            Cell cell = game.getCell(col, row);
		            if (cell != null) {
		                Wall wallObj = new Wall(cell);
		                game.addWall(wallObj);
		            }
	    		} else if (line.charAt(i) == 'E') { // If program is on E
	    			int col = i;
		            int row = lineRow - 1;
		            // Add exit to the game object at column and row - 1 to account for extra line
		            Cell cell = game.getCell(col, row);
		            if (cell != null) {
		                Exit exitObj = new Exit(cell);
		                game.addExit(exitObj);
		            }
	    		}
	    	}
	    }
	}
}
