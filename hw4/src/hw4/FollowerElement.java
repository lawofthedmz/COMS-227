package hw4;

import api.AbstractElement;

/**
 * 
 * Description of the class: The FollowerElement class is a subclass of the
 * AttachedElement class. The class has private fields for the minimum and maximum x 
 * Basically, the FollowerElement class represents an element that follows another element.
 * It is almost the same as an attached element but with a few differences.
 * 
 * A follower element is one that is associated with another "base" element such
 * as a PlatformElement or LiftElement. Specifically, the follower element's
 * movement is determined by the movement of the base element, when the base
 * move up 10 pixels, the follower moves up 10 pixels. However, the follower may
 * not always be at a fixed location relative to the base. When the horizontal
 * velocity of the follower is set to a non-zero value, the follower will
 * oscillate between the left and right edges of the PlatformElement or
 * LiftElement it is associated with.
 * 
 * @author Murphy Glawe
 */
public class FollowerElement extends AttachedElement{
	
	// Private fields
	private double min; // min x value of FollowerElement
	private double max; // max x value of FollowerElement
	private int offset; // Offset from base element


	/**
	 * Constructs a new FollowerElement. Before being added to a "base" element such
	 * as a PlatformElement or LiftElement, the x and y coordinates are zero. When a
	 * base element is set, the initial x-coordinate becomes the base's
	 * x-coordinate, plus the given offset, and the y-coordinate becomes the base's
	 * y-coordinate, minus this element's height.
	 * 
	 * @param width         element's width
	 * @param height        element's height
	 * @param initialOffset when added to a base, this amount will be added to the
	 *                      bases's x-coordinate to calculate this element's initial
	 *                      x-coordinate
	 */
	public FollowerElement(int width, int height, int initialOffset) {
        super(width, height, 0, 0); // Call AttachedElement constructor		
        this.offset = initialOffset;
	}
	
	/**
	 * Sets the base element for this follower element.
	 * 
	 * @param b the base element to set
	 */
	public void setBase(AbstractElement b) {
        if (b != null) {
        	setPosition(b.getXInt() + offset, b.getYInt() - getHeight());
            setBounds(b.getXInt(), b.getXInt() + b.getWidth());
            setBaseElement(b);
        }

	}

	/**
     * Sets the left and right boundaries for this follower element's movement.
     * 
     * @param min the left boundary
     * @param max the right boundary
     */
    public void setBounds(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    /**
     * Returns the left boundary for this follower's movement.
     * 
     * @return the left boundary
     */
    public double getMin() {
        return min;
    }

    /**
     * Returns the right boundary for this follower's movement.
     * 
     * @return the right boundary
     */
    public double getMax() {
        return max;
    }

    @Override
    public void update() { // Update the position based on velocity and call the superclass update method
    	super.update(); // Call the superclass update method
        if  (getBaseElement() != null) {
            // Calculate the new x-coordinate based on the base element's x-coordinate
            setBounds(getBaseElement().getXReal(), getBaseElement().getXReal() + getBaseElement().getWidth()); 
        }
    }
}	



