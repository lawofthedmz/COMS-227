package hw4;

/**
 * An element that does not move. Instead, it is intended to appear on the
 * screen for a fixed number of frames.
 *<p>
 * This class represents a vanishing element in a 2D video game. It extends the
 * SimpleElement class and implements functionality to decrement its remaining
 * life each frame. When the remaining life reaches zero, the element is marked
 * for deletion.
 * </p>
 * <p>
 * The position of the element is represented by its upper-left corner
 * coordinates (x, y). The width and height of the element define its size.
 * </p>
 * <p>
 * This class inherits the frame counting and deletion marking behavior from
 * the AbstractElement class and adds functionality to decrement its remaining
 * life and mark for deletion when necessary.
 * </p>
 * 
 * @author Murphy Glawe
 */
public class VanishingElement extends SimpleElement{
	
	// Private field to store the remaining life of the element	
	private int life;
	
	/**
	 * Constructs a new VanishingElement.
	 * 
	 * @param x           x-coordinate of upper left corner
	 * @param y           y-coordinate of upper left corner
	 * @param width       element's width
	 * @param height      element's height
	 * @param initialLife the number of frames until this element marks itself for
	 *                    deletion
	 */
	public VanishingElement(double x, double y, int width, int height, int initialLife) {
		super(x, y, width, height);
        this.life = initialLife;

	}
	
    @Override
    public void update() {
        super.update();
        // Mark the element for deletion if the life has expired
        if (getFrameCount() >= life) {
            markForDeletion();
        }
    }
}
