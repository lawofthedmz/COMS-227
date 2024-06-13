package hw4;

import java.util.ArrayList;

/**
 * A PlatformElement is an element with two distinctive behaviors. First, it can
 * be set up to move horizontally within a fixed set of boundaries. On reaching
 * a boundary, the x-component of its velocity is reversed. Second, it maintains
 * a list of <em>associated</em> elements whose basic motion all occurs relative
 * to the PlatformElement.
 * 
 * @author Murphy Glawe
 */
public class PlatformElement extends MovingElement{
	
	// Private fields
    private double min; // min x value of PlatformElement (left boundary);
    private double max; // max x value of PlatformElement (right boundary);
    private ArrayList<AttachedElement> associatedElements; // List of associated elements
    

	/**
	 * Constructs a new PlatformElement. Initially the left and right boundaries are
	 * <code>Double.NEGATIVE_INFINITY</code> and
	 * <code>Double.POSITIVE_INFINITY</code>, respectively.
	 * 
	 * @param x      x-coordinate of initial position of upper left corner
	 * @param y      y-coordinate of initial position of upper left corner
	 * @param width  object's width
	 * @param height object's height
	 */
	public PlatformElement(double x, double y, int width, int height) {
		 super(x, y, width, height); // Call MovingElement constructor with initial position (x, y)
	        associatedElements = new ArrayList<>(); // Initialize associated elements list
	        setBounds(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY); // Set default boundaries
	}
	
	/**
    * Sets the left and right boundaries for this Platform's movement.
    * 
    * @param min the min boundary
    * @param max the max boundary 
    */
   public void setBounds(double min, double max) {
       this.min = min;
       this.max = max;
   }
   
   /**
	 * 
	 * @return returns max value of object
	 */
	public double getMax() {
		return max;
	}
	
	/**
	 * 
	 * @return returns min value of object
	 */
	public double getMin() {
		return min;
	}
   /**
    * Adds an associated element to this element, and sets this object to be the
    * AttachedElement's base.
    * 
    * @param attached the associated element to add
    */
   public void addAssociated(AttachedElement attached) {
       associatedElements.add(attached);
       attached.setBase(this);
   }

   /**
    * Adds an associated element to this element, and sets this object to be the
    * FollowerElement's base.
    * 
    * @param follower the associated element to add
    */
   public void addAssociated(FollowerElement follower) {
       associatedElements.add(follower);
       follower.setBase(this);
   }

   /**
    * Returns a list of all this objects associated elements.
    * 
    * @return a list of associated elements
    */
   public ArrayList<AttachedElement> getAssociated() {
       return associatedElements;
   }
   
   /**
    * Deletes all associated elements that have been marked for deletion.
    */
   public void deleteMarkedAssociated() {
       // Create a temporary list to store elements to be deleted
       ArrayList<AttachedElement> elementsToRemove = new ArrayList<>();
       // Iterate over the associated elements
       for (AttachedElement element : associatedElements) {
           // Check if the element is marked for deletion
           if (element.isMarked()) {
               // Add the element to the list of elements to be removed
               elementsToRemove.add(element);
           }
       }
       // Remove the marked elements from the associated list
       getAssociated().removeAll(elementsToRemove);
   }

   @Override
   public void update() {		
		double velocity = getDeltaX(); // Get current velocity
		if(getDeltaX() + getXReal() + getWidth() >= max) { // If the new x-coordinate is greater than the right boundary
			setVelocity((max - (getXReal() + getWidth())), getDeltaY());
			super.update();
			setVelocity((-velocity), getDeltaY());
		} else if(getDeltaX() + getXReal() <= min) { // If the new x-coordinate is less than the left boundary
			setVelocity((min - getXReal()), getDeltaY());
			super.update();
			setVelocity((-velocity), getDeltaY());
		} else { // If the new x-coordinate is within the boundaries
			super.update();
		}
		for(AttachedElement element: getAssociated()) { // Iterate over associated elements
           element.update(); // Update the associated element			
		}
   }
}
