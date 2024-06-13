package hw4;

import api.AbstractElement;

/**
 * The AttachedElement class is a subclass of the MovingElement class. 
 * The class has private fields for the base element, offset from the base element, and hover amount. 
 * The constructor initializes the AttachedElement with a width, height, offset, and hover amount. 
 * When the base object is set using the setBase method, the initial position of the AttachedElement is calculated based on the base object's coordinates, offset, and hover amount.
 * The class provides getter methods for the offset and hover amount, as well as setter and getter methods for the base element. 
 * The update method updates the position of the AttachedElement based on the base element's velocity. 
 * It also handles boundary conditions and adjusts the position and velocity accordingly.
 * An attached element is one that is associated with another "base" element
 * such as a PlatformElement or a LiftElement. Specifically, the attached
 * element's movement is determined by the movement of the base element, the
 * element always remains a fixed distance away.
 * 
 * @author Murphy Glawe
 */
public class AttachedElement extends MovingElement{
	
	// Private fields
    private AbstractElement baseElement; // The base element
    private int offset; // Offset from base element
    private int hover; // Hover amount

	/**
	 * Constructs a new AttachedElement. Before being added to an associated "base"
	 * element such as a PlatformElement or LiftElement, the x and y coordinates are
	 * initialized to zero. When the base object is set (not in this constructor),
	 * the x-coordinate will be calculated as the base object's x-coordinate, plus
	 * the given offset, and the y-coordinate will become the base object's
	 * y-coordinate, minus this element's height, minus the hover amount.
	 * 
	 * @param width  element's width
	 * @param height element's height
	 * @param offset when added to a base object, this amount will be added to the
	 *               other object's x-coordinate to calculate this element's
	 *               x-coordinate
	 * @param hover  when added to a base object, this element's y-coordinate is the
	 *               other object's y-coordinate, minus this element's height, minus
	 *               the hover amount
	 */
	public AttachedElement(int width, int height, int offset, int hover) {
		super(0, 0, width, height); // Call MovingElement constructor with initial position (0, 0)
		this.offset = offset;
		this.hover = hover;
	}
    
	
	/**
     * Sets the base element for this attached element.
     * 
     * @param b the base element to set
     */
    public void setBase(AbstractElement b) {
        setBaseElement(b);
        if (baseElement != null) {
            // Calculate initial position relative to the base element
            setPosition(baseElement.getXReal() + offset, baseElement.getYReal() - getHeight() - hover);
        }
    }
    
    /**
     * Helper methods to get the base element and the offset of the attached element and the hover amount
     */
    
	/**
	 * Returns the offset of this attached element.
	 * 
	 * @return offset
	 */
     protected int getOffset() {
    	 return offset;
     }
     
	/**
	 * Returns the hover amount of this attached element.
	 * 
	 * @return hover
	 */
	protected int getHover() {
		return hover;
	}
	
	 /**
	  * Sets the base element for this attached element.
	  * @param baseElement
	  */
	protected void setBaseElement(AbstractElement b) {
		this.baseElement = b;
	}
    
    /**
     * Returns the base element for this attached element.
     * @return baseElement
     */
    protected AbstractElement getBaseElement() {
    	return baseElement;
    }

	@Override 
	public void update() { // Update the position based on the base element's velocity
		if (baseElement != null) {
			PlatformElement platform = (PlatformElement) baseElement; // Cast baseElement to PlatformElement (THIS PART SUCKS) 
			double deltaX = getDeltaX(); // Get current velocity of the element // YOU HAVE TO GET VELOCITY BEFORE SETTING PLATORM VELOCITY
			setVelocity(platform.getDeltaX() + getDeltaX(), getDeltaY()); // Set the velocity of the element to the velocity of the platform
			super.update(); // Call the superclass update method
			setVelocity(deltaX, getDeltaY()); // Reset the velocity of the element
			
			// Handle boundary conditions
			if(getOffset() != 0) { // If the element has an offset
				if(getXReal() > (baseElement.getXReal() + getOffset())) { // If the element is to the right of the base element
					setPosition((baseElement.getXReal() + getOffset()), getYReal()); // Set the position of the element to the base element's x-coordinate plus the offset
				}
			} else if(getXReal() >= (baseElement.getXReal() + baseElement.getWidth() - getWidth())) { // If the element is at the right boundary
				setPosition(baseElement.getXReal() + baseElement.getWidth() - getWidth(), getYReal()); // Set the position of the element to the base element's x-coordinate plus the width of the base element minus the width of the element
				setVelocity(-getDeltaX(), getDeltaY()); // Reverse the x-velocity of the element
			}
			if(getXReal() <= (baseElement.getXReal() + getOffset())) { // If the element is at the left boundary
				setPosition(baseElement.getXReal() + getOffset(), getYReal()); // Set the position of the element to the base element's x-coordinate plus the offset
				setVelocity(-getDeltaX(), getDeltaY()); // Reverse the x-velocity of the element
			} 
		}
	}
    

    

    

}
