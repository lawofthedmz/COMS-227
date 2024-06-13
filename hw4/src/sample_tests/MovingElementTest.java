package sample_tests;

import hw4.MovingElement;

public class MovingElementTest {

    public static void main(String[] args) {
        testInitialVelocity();
        testPositionUpdate();
    }

    public static void testInitialVelocity() {
        MovingElement element = new MovingElement(0, 0, 10, 10);
        double initialDeltaX = 1.5;
        double initialDeltaY = -0.5;

        element.setVelocity(initialDeltaX, initialDeltaY);

        double deltaX = element.getDeltaX();
        double deltaY = element.getDeltaY();

        System.out.println("Initial velocity:");
        System.out.println("Expected deltaX: " + initialDeltaX);
        System.out.println("Expected deltaY: " + initialDeltaY);
        System.out.println("Actual deltaX: " + deltaX);
        System.out.println("Actual deltaY: " + deltaY);
    }

    public static void testPositionUpdate() {
        MovingElement element = new MovingElement(0, 0, 10, 10);
        double initialX = 10;
        double initialY = 20;
        double deltaX = 2.5;
        double deltaY = -1.5;

        element.setPosition(initialX, initialY);
        element.setVelocity(deltaX, deltaY);

        element.update(); // Update position

        double newX = element.getXReal();
        double newY = element.getYReal();

        System.out.println("\nPosition update:");
        System.out.println("Initial position: (" + initialX + ", " + initialY + ")");
        System.out.println("Velocity: (" + deltaX + ", " + deltaY + ")");
        System.out.println("Expected position: (" + (initialX + deltaX) + ", " + (initialY + deltaY) + ")");
        System.out.println("Actual position: (" + newX + ", " + newY + ")");
    }
}