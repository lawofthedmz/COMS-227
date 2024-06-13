package hw4;

/**
 * Platform element that moves vertically within a fixed set of boundaries. 
 * 
 * An element with two distinctive behaviors. First, it can be set up to move
 * vertically within a fixed set of boundaries. On reaching a boundary, the
 * y-component of its velocity is reversed. Second, it maintains a list of
 * <em>associated</em> elements whose basic motion all occurs relative to the
 * LiftElement.
 * 
 * @author Murphy Glawe
 */
public class LiftElement extends PlatformElement{
	
	// Private fields
	private double min; // min y value of LiftElement
	private double max; // max y value of LiftElement
 

	/**
	 * Constructs a new Elevator. Initially the upper and lower boundaries are
	 * <code>Double.NEGATIVE_INFINITY</code> and
	 * <code>Double.POSITIVE_INFINITY</code>, respectively.
	 * 
	 * @param x      x-coordinate of initial position of upper left corner
	 * @param y      y-coordinate of initial position of upper left corner
	 * @param width  element's width
	 * @param height element's height
	 */
	public LiftElement(double x, double y, int width, int height) {
		super(x, y, width, height); // Call PlatformElement constructor with initial position (x, y)
        setBounds(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY); // Set default boundaries
	}
	
	@Override
	public double getMin() { // Returns the min y value of the object
		return min;
	} 
	
	@Override
	public double getMax() { // Returns the max  y value of the object
		return max;
	}
	
	@Override
	public void setBounds(double min, double max) { // Sets the upper and lower boundaries for this LiftElement's movement
		this.min = min;
		this.max = max;
		super.setBounds(getXReal(), getXReal() + getWidth()); // Set the x boundaries of the object by calling platform element's setBounds method
	}
    
    @Override
    public void update() {
    	super.update(); // Call the update method of the superclass
		if (getYReal() >= getMax() - getHeight()) { // If the new y-coordinate is less than the upper boundary
			setPosition(getXReal(), getMax() - getHeight());	
			setVelocity(getDeltaX(), -getDeltaY());

		} else if (getYReal() <= getMin()) { // If the new y-coordinate is greater than the lower boundary
			setPosition(getXReal(), getMin());
			setVelocity(getDeltaX(), -getDeltaY());
		}
		for(AttachedElement element: getAssociated()) { // For each associated element
			element.setPosition(element.getXReal(), getYReal() - element.getHeight() - element.getHover());
		} 
    }
}