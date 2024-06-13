package hw4;

import java.awt.Rectangle;

import api.AbstractElement;
/**
 * Minimal concrete extension of AbstractElement. The <code>update</code> method
 * in this implementation just increments the frame count.
 * <p>
 * This class represents a simple element in a 2D video game. It extends the
 * AbstractElement class and implements basic functionality such as updating
 * the frame count, marking for deletion, and checking for collisions with
 * other elements.
 * </p>
 * <p>
 * The position of the element is represented by its upper-left corner
 * coordinates (x, y). The width and height of the element define its size.
 * </p>
 * <p>
 * The class hierarchy for the elements is organized to allow for different
 * types of elements with specific behaviors to be easily defined by
 * extending AbstractElement and implementing custom functionality.
 * </p>
 * 
 * @author Murphy Glawe
 */
public class SimpleElement extends AbstractElement {
	// Private fields
    private double x; // x-coordinate of upper left corner
    private double y; // y-coordinate of upper left corner
    private int width; // element's width
    private int height; // element's height
    private int frameCount; // frame count
    private boolean markedForDeletion; // whether the element is marked for deletion

	/**
	 * Constructs a new SimpleElement.
	 * 
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  element's width
	 * @param height element's height
	 */
	public SimpleElement(double x, double y, int width, int height) {
		setPosition(x, y);
		frameCount = 0;
		this.width = width;
		this.height = height;
		markedForDeletion = false;
	}

	@Override
	public int getXInt() {
		// Return the x-coordinate of the element as an integer
        return (int) Math.round(getXReal());
	}

	@Override
	public int getYInt() {
		// Return the y-coordinate of the element as an integer
		return (int) Math.round(getYReal());
	}

	@Override
	public int getWidth() {
		// Return the width of the element
		return this.width;
	}

	@Override
	public int getHeight() {
		// Return the height of the element
		return this.height;
	}

	@Override
	public Rectangle getRect() {
		// Return a rectangle representing the element's bounding box
		return new Rectangle(getXInt(), getYInt(), getWidth(), getHeight());
	}

	@Override
	public void setPosition(double newX, double newY) {
        // Set the position of the element
		this.x = newX;
		this.y = newY;
		
	}

	@Override
	public double getXReal() {
        // Return the x-coordinate of the element
		return this.x;
	}

	@Override
	public double getYReal() {
        // Return the y-coordinate of the element
		return this.y;
	}

	@Override
	public void update() {
		// Increment the frame count
		frameCount++;
	}

	@Override
	public int getFrameCount() {
		// Return the frame count
		return frameCount;
	}

	@Override
	public boolean isMarked() {
		// Return whether the element is marked for deletion
		return markedForDeletion;
	}

	@Override
	public void markForDeletion() {
		// Mark the element for deletion
		markedForDeletion = true;
		
	}

	@Override
	public boolean collides(AbstractElement other) {
        // Check if this element collides with the given element
		Rectangle r1 = getRect();
		Rectangle r2 = other.getRect();
		return r1.intersects(r2);
	}
		

}
