package hw4;

/**
 * An element in which the <code>update</code> method updates the position each
 * frame according to a <em>velocity</em> vector (deltaX, deltaY). The units are
 * assumed to be "pixels per frame".
 * <p>
 * This class extends the SimpleElement class and adds functionality to update
 * the position of the element based on a velocity vector. The velocity vector
 * consists of deltaX and deltaY, representing the change in position in the
 * x and y directions, respectively, per frame.
 * </p>
 * <p>
 * By setting the velocity using the <code>setVelocity</code> method, and calling
 * the <code>update</code> method each frame, the element will move smoothly
 * across the screen.
 * </p>
 * <p>
 * The class inherits the basic properties and behavior from its superclass
 * SimpleElement, such as position, size, frame count, marking for deletion,
 * and collision detection.
 * </p>
 * 
 * @author Murphy Glawe
 */
public class MovingElement extends SimpleElement{
	
	// Fields to store velocity
    private double deltaX; // Velocity in the x-direction
    private double deltaY; // Velocity in the y-direction

	/**
	 * Constructs a MovingElement with a default velocity of zero in both
	 * directions.
	 * 
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  object's width
	 * @param height object's height
	 */
	public MovingElement(double x, double y, int width, int height) {
		super(x, y, width, height);
		this.deltaX = 0;
        this.deltaY = 0;
		
	}
	
	/**
	 * Returns the velocity of this element in the x-direction.
	 * 
	 * @return velocity in the x-direction (pixels per frame)
	 */
	public double getDeltaX() {
		return deltaX;
	}

	/**
	 * Returns the velocity of this element in the y-direction.
	 * 
	 * @return velocity in the y-direction (pixels per frame)
	 */
	public double getDeltaY() {
		return deltaY;
	}

	/**
     * Sets the velocity of this element.
     * 
     * @param deltaX velocity in the x-direction (pixels per frame)
     * @param deltaY velocity in the y-direction (pixels per frame)
     */
    public void setVelocity(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override 
    public void update() {
        // Update the position based on velocity and call the superclass update method
        double newX = getXReal() + deltaX;
        double newY = getYReal() + deltaY;
        setPosition(newX, newY);
        super.update(); 
    }


}
