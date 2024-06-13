package hw3;
import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Exit;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Wall;
import api.BodySegment;

/**
 * Class that models a game.
 * @author murph
 */
public class LizardGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
    private Cell[][] grid; // 2D array representing the grid of cells
    private ArrayList<Lizard> lizards; // List of lizards on the grid
    private int width; // Number of columns in the grid
    private int height; // Number of rows in the grid

	/**
	 * Constructs a new LizardGame object with given grid dimensions.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public LizardGame(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new Cell[width][height];
		lizards = new ArrayList<>();
		
		// Initialize cells in the grid
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                grid[col][row] = new Cell(col, row); // Assuming Cell constructor exists
            }
        }
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width of the grid
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height of the grid
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Adds a wall to the grid.
	 * <p>
	 * Specifically, this method calls placeWall on the Cell object associated with
	 * the wall (see the Wall class for how to get the cell associated with the
	 * wall). This class assumes a cell has already been set on the wall before
	 * being called.
	 * 
	 * @param wall to add
	 */
	public void addWall(Wall wall) {
		Cell wallCell = wall.getCell();
		if (wallCell != null) {
	        wallCell.removeWall(); // Remove any existing wall on the cell
	        wallCell.placeWall(wall);
	    }
	}

	/**
	 * Adds an exit to the grid.
	 * <p>
	 * Specifically, this method calls placeExit on the Cell object associated with
	 * the exit (see the Exit class for how to get the cell associated with the
	 * exit). This class assumes a cell has already been set on the exit before
	 * being called.
	 * 
	 * @param exit to add
	 */
	public void addExit(Exit exit) {
		Cell exitCell = exit.getCell();
	    if (exitCell != null) {
	        exitCell.removeExit(); // Remove any existing exit on the cell
	        exitCell.placeExit(exit);
	    }
	}

	/**
	 * Gets a list of all lizards on the grid. Does not include lizards that have
	 * exited.
	 * 
	 * @return lizards list of lizards
	 */
	public ArrayList<Lizard> getLizards() {
		return lizards;
	}

	/**
	 * Adds the given lizard to the grid.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards.
	 * 
	 * @param lizard to add
	 */
	public void addLizard(Lizard lizard) {
		// Add the lizard to the list of lizards
	    lizards.add(lizard);
	    
	    // Update the score by notifying the score listener
	    if (scoreListener != null) {
	        scoreListener.updateScore(lizards.size());
	    }
	}

	/**
	 * Removes the given lizard from the grid. Be aware that each cell object knows
	 * about a lizard that is placed on top of it. It is expected that this method
	 * updates all cells that the lizard used to be on, so that they now have no
	 * lizard placed on them.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards using
	 * updateScore().
	 * 
	 * @param lizard to remove
	 */
	public void removeLizard(Lizard lizard) {
		// Remove the lizard from the list of lizards
	    lizards.remove(lizard);
	    
	    // Update the score by notifying the score listener
	    if (scoreListener != null) {
	        scoreListener.updateScore(lizards.size());
	    }
	    
	    // Remove all segments of the lizard from the grid
	    for (BodySegment segment : lizard.getSegments()) {
	        Cell cell = segment.getCell();
	        if (cell != null) {
	            cell.removeLizard(); // Clear the cell from the lizard
	        }
	    }
	}

	/**
	 * Gets the cell for the given column and row.
	 * <p>
	 * If the column or row are outside of the boundaries of the grid the method
	 * returns null.
	 * 
	 * @param col column of the cell
	 * @param row of the cell
	 * @return the cell or null
	 */
	public Cell getCell(int col, int row) {
		// Check if the column and row are within the boundaries of the grid
	    if (col >= 0 && col < width && row >= 0 && row < height) {
	        // Return the cell at the specified coordinates
	        return grid[col][row];
	    }
	    // Return null if the coordinates are outside the grid
	    return null;
	}

	/**
	 * Gets the cell that is adjacent to (one over from) the given column and row,
	 * when moving in the given direction. For example (1, 4, UP) returns the cell
	 * at (1, 3).
	 * <p>
	 * If the adjacent cell is outside of the boundaries of the grid, the method
	 * returns null.
	 * 
	 * @param col the given column
	 * @param row the given row
	 * @param dir the direction from the given column and row to the adjacent cell
	 * @return the adjacent cell or null
	 */
	public Cell getAdjacentCell(int col, int row, Direction dir) {
		// Calculate the column and row of the adjacent cell based on the direction
	    int adjCol = col;
	    int adjRow = row;
	    switch (dir) {
	        case UP:
	            adjRow--;
	            break;
	        case DOWN:
	            adjRow++;
	            break;
	        case LEFT:
	            adjCol--;
	            break;
	        case RIGHT:
	            adjCol++;
	            break;
	    }

	    // Check if the adjacent cell is within the boundaries of the grid
	    if (adjCol >= 0 && adjCol < width && adjRow >= 0 && adjRow < height) {
	        return grid[adjCol][adjRow]; // Return the adjacent cell
	    } else {
	        return null; // Return null if the adjacent cell is outside the grid
	    }
	}

	/**
	 * Resets the grid. After calling this method the game should have a grid of
	 * size width x height containing all empty cells. Empty means cells with no
	 * walls, exits, etc.
	 * <p>
	 * All lizards should also be removed from the grid.
	 * 
	 * @param width  number of columns of the resized grid
	 * @param height number of rows of the resized grid
	 */
	public void resetGrid(int width, int height) {
		this.width = width;
	    this.height = height;
	    this.grid = new Cell[width][height]; // Create a new grid with the specified dimensions
	    lizards.clear(); // Remove all lizards from the grid
	    
	    // Initialize cells in the grid
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                grid[col][row] = new Cell(col, row); // Assuming Cell constructor exists
            }
        }
	}

	/**
	 * Returns true if a given cell location (col, row) is available for a lizard to
	 * move into. Specifically the cell cannot contain a wall or a lizard. Any other
	 * type of cell, including an exit is available.
	 * 
	 * @param row of the cell being tested
	 * @param col of the cell being tested
	 * @return true if the cell is available, false otherwise
	 */
	public boolean isAvailable(int col, int row) {
		Cell cell = getCell(col, row);
	    	// Check if the given position is within the grid boundaries
		    if (col < 0 || col > width || row < 0 || row > height) {
		        return false; // Not available if outside grid 
		    }
	    	
	    	if (cell.getExit() != null) {
	            return true; // Cell contains an exit, available
	        }
	        // For non-exit cells, check if it's empty (does not contain a lizard)
	        if (cell.getWall() != null || cell.getLizard() != null) {
	            return false; // Cell contains a lizard, not available
	        }
		    return true; // Cell does not contain exit lizard or wall 

	    	
	}

	/**
	 * Move the lizard specified by its body segment at the given position (col,
	 * row) one cell in the given direction. The entire body of the lizard must move
	 * in a snake like fashion, in other words, each body segment pushes and pulls
	 * the segments it is connected to forward or backward in the path of the
	 * lizard's body. The given direction may result in the lizard moving its body
	 * either forward or backward by one cell.
	 * <p>
	 * The segments of a lizard's body are linked together and movement must always
	 * be "in-line" with the body. It is allowed to implement movement by either
	 * shifting every body segment one cell over or by creating a new head or tail
	 * segment and removing an existing head or tail segment to achieve the same
	 * effect of movement in the forward or backward direction.
	 * <p>
	 * If any segment of the lizard moves over an exit cell, the lizard should be
	 * removed from the grid.
	 * <p>
	 * If there are no lizards left on the grid the player has won the puzzle the
	 * the dialog listener should be used to display (see showDialog) the message
	 * "You win!".
	 * <p>
	 * It is possible that the given direction is not in-line with the body of the
	 * lizard (as described above), in that case this method should do nothing.
	 * <p>
	 * It is possible that the given column and row are outside the bounds of the
	 * grid, in that case this method should do nothing.
	 * <p>
	 * It is possible that there is no lizard at the given column and row, in that
	 * case this method should do nothing.
	 * <p>
	 * It is possible that the lizard is blocked and cannot move in the requested
	 * direction, in that case this method should do nothing.
	 * <p>
	 * <b>Developer's note: You may have noticed that there are a lot of details
	 * that need to be considered when implement this method method. It is highly
	 * recommend to explore how you can use the public API methods of this class,
	 * Grid and Lizard (hint: there are many helpful methods in those classes that
	 * will simplify your logic here) and also create your own private helper
	 * methods. Break the problem into smaller parts are work on each part
	 * individually.</b>
	 * 
	 * @param col the given column of a selected segment
	 * @param row the given row of a selected segment
	 * @param dir the given direction to move the selected segment
	 */
	public void move(int col, int row, Direction dir) {

		if (col < 0 || col >= width || row < 0 || row >= height) {
	        return ; // Not available if outside grid 
	    }
	    // Get the current cell based on the given position
	    Cell currentCell = getCell(col, row);
	    if (currentCell == null) {
	        return; // Do nothing if cell is null
	    }

	    // Get the lizard on the current cell
	    Lizard lizard = currentCell.getLizard();
	    if (lizard == null) {
	        return; // Do nothing if no lizard on the cell
	    }
	    
	    //Gets the list of segments
	    ArrayList<BodySegment> segments = lizard.getSegments();

	    // Get the direction the lizard is facing
	    Direction lizardDirection = lizard.getHeadDirection();
	    if (lizardDirection == null) {
	        return; // Do nothing if lizard direction is null
	    }

	    // Get the cell lizard is moving to 
	    Cell nextCell = getAdjacentCell(col, row, dir);
	    if (nextCell == null) {
	        return; // Do nothing if nextCell is null
	    }
	      
	    // Check if the cell is available 
	    if (isAvailable(nextCell.getCol(), nextCell.getRow())) {	           
	        
	    	// Determine if the lizard is moving forward or backward
	        // Forward from Head 
	        if (lizard.getSegmentAt(currentCell) == lizard.getHeadSegment()) {	            
	        	// Move
	        	headMovement(lizard, nextCell, segments);	           
	        } 	        
	        
	        // Backward from Tail 
	        else if (lizard.getSegmentAt(currentCell) == lizard.getTailSegment()) {
	        	// Move
	        	tailMovement(lizard, nextCell, segments);
	        }
	    } // Check if lizard is moving to a segment in the segments list 
	    else if (segments.contains(lizard.getSegmentAt(nextCell)) ){
	    	// If lizard is moving forward
	    	if (lizard.getDirectionToSegmentAhead(lizard.getSegmentAt(currentCell)) == dir) {
		    	// Sets cell the head will move to if push towards head
		        Cell headAutoAdjacent = getAdjacentCell(lizard.getHeadSegment().getCell().getCol(), lizard.getHeadSegment().getCell().getRow(), lizard.getHeadDirection());
		        if (headAutoAdjacent == null) {
		        	return;
		        } // Checks availability of where head is moving to
		        if (isAvailable(headAutoAdjacent.getCol(), headAutoAdjacent.getRow())) {
		        	// Move
		        	headMovement(lizard, headAutoAdjacent, segments);
		        	
		        	
			        }
	    	} // If lizard is moving backwards 
	    	else if (lizard.getDirectionToSegmentBehind(lizard.getSegmentAt(currentCell)) == dir) {
	    		// Sets cell the tail will move to if pull towards tail
		        Cell tailAutoAdjacent = getAdjacentCell(lizard.getTailSegment().getCell().getCol(), lizard.getTailSegment().getCell().getRow(), lizard.getTailDirection());   
		    	if (tailAutoAdjacent == null) {
		    		return;
		    	} // Checks availability of where tail is moving to
		        if (isAvailable(tailAutoAdjacent.getCol(), tailAutoAdjacent.getRow())) {
		        	// Move
		        	tailMovement(lizard, tailAutoAdjacent, segments);
		        	
		        }
	    	}
	    } 

	    // Check if lizard is on an exit cell and remove it from the grid and list if so
	    if (lizard.getHeadSegment().getCell().getExit() != null || lizard.getTailSegment().getCell().getExit() != null) {
	        removeLizard(lizard);	        
	        if (lizards.size() == 0) {
	        	dialogListener.showDialog("\"You Win!\"");	
	        	
	        }	
	    }
	}
	
	
	/**
	 * Moves the tail of the lizard to the next cell and updates the positions of the rest of the lizard's body segments.
	 * 
	 * @param lizard   the lizard object
	 * @param nextCell the next cell where the tail will be moved to
	 * @param segments the list of body segments of the lizard
	 */
	private void tailMovement(Lizard lizard, Cell nextCell, ArrayList<BodySegment> segments) {
	    // Set previous cell
	    Cell previousCell = lizard.getTailSegment().getCell();	            
	    // Move tail to next cell
	    lizard.getTailSegment().setCell(nextCell);
	    // Move rest of the lizard 
	    for (int i = 1; i <= segments.size() - 1; i++) {
	        Cell temporaryCell = segments.get(i).getCell();
	        segments.get(i).setCell(previousCell);
	        previousCell = temporaryCell;
	    }
	    // Clear the cell previously occupied by the tail
	    previousCell.removeLizard();
	}

	
	/**
	 * Moves the head of the lizard to the specified next cell, and updates the positions of the rest of the lizard segments accordingly.
	 * The previous cell occupied by the head is cleared.
	 *
	 * @param lizard   the lizard object whose head is being moved
	 * @param nextCell the cell to which the head is being moved
	 * @param segments the list of body segments of the lizard
	 */
	private void headMovement(Lizard lizard, Cell nextCell, ArrayList<BodySegment> segments) {
	    // Set previous cell
	    Cell previousCell = lizard.getHeadSegment().getCell();
	    // Move Head to next cell
	    lizard.getHeadSegment().setCell(nextCell);
	    // Move rest of the lizard 
	    for (int i = segments.size() - 2; i >= 0; i--) {
	        Cell temporaryCell = segments.get(i).getCell();
	        segments.get(i).setCell(previousCell);
	        previousCell = temporaryCell;
	    }
	    // Clear the cell previously occupied by the tail
	    previousCell.removeLizard();
	}
	
	

	


	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}

	@Override
	public String toString() {
		String str = "---------- GRID ----------\n";
		str += "Dimensions:\n";
		str += getWidth() + " " + getHeight() + "\n";
		str += "Layout:\n";
		for (int y = 0; y < getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			for (int x = 0; x < getWidth(); x++) {
				str += getCell(x, y);
			}
		}
		str += "\nLizards:\n";
		for (Lizard l : getLizards()) {
			str += l;
		}
		str += "\n--------------------------\n";
		return str;
	}
}




