package hw4;

/**
 * Moving element in which the vertical velocity is adjusted each frame by a
 * gravitational constant to simulate gravity. The element can be set to
 * "grounded", meaning gravity will no longer influence its velocity.
 * <p>
 * This class extends the MovingElement class and adds functionality to simulate gravity.
 * The gravitational constant can be adjusted using the setGravity method. The element
 * can also be set to grounded, meaning gravity will no longer affect its velocity.
 * </p>
 * <p>
 * By setting the gravity and calling the update method each frame, the element will
 * move smoothly and be influenced by gravity.
 * </p>
 * <p>
 * The class inherits the properties and behavior of its superclass MovingElement, including
 * position, velocity, size, frame count, marking for deletion, and collision detection.
 * </p>
 * 
 * @author Murphy Glawe
 */
public class FlyingElement extends MovingElement {
	
	// Fields to store gravitational constant and grounded status
    private double gravity; // Gravitational constant
    private boolean grounded; // Grounded status of element
 
	/**
	 * Constructs a new FlyingElement. By default it should be grounded, meaning
	 * gravity does not influence its velocity.
	 * 
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  element's width
	 * @param height element's height
	 */
	public FlyingElement(double x, double y, int width, int height) {
		super(x, y, width, height);
		grounded = true; // default grounded status
	}
	
	/**
     * Sets the gravitational constant for this element.
     * 
     * @param gravity the gravitational constant
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    
    /**
     * Checks whether the element is currently grounded.
     * 
     * @return true if the element is grounded, false otherwise
     */
    public boolean isGrounded() {
        return grounded;
    }

    /**
     * Sets the grounded status of this element.
     * 
     * @param grounded true if the element is grounded, false otherwise
     */
    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    @Override
    public void update() {
        // Call the superclass update method
        super.update();  
        // Adjust the y-component of velocity based on gravity
        if (!isGrounded()) {
            double newDeltaY = getDeltaY() + gravity;
            setVelocity(getDeltaX(), newDeltaY);
        }
    }

}
