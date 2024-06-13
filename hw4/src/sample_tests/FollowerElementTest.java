package sample_tests;

import hw4.FollowerElement;
import hw4.MovingElement;

public class FollowerElementTest {

    public static void main(String[] args) {
        testFollowerUpdate();
    }

    public static void testFollowerUpdate() {
        // Create a moving element with initial position (0, 0) and velocity (2.0, 0.0)
        MovingElement movingElement = new MovingElement(0, 0, 10, 10);
        movingElement.setVelocity(2.0, 0.0);

        // Create a follower element with initial offset 0
        FollowerElement followerElement = new FollowerElement(10, 10, -4);
        // Set the base element of the follower element to be the moving element
        followerElement.setBase(movingElement);

        // Update the position of the moving element
        movingElement.update();
        // Update the position of the follower element
        followerElement.update();

        // Verify the position of the follower element after the moving element updates
        double followerExpectedX = -2; // Expected X position of the follower after one update
        double followerActualX = followerElement.getXReal(); // Actual X position of the follower
        double epsilon = 0.0001; // Tolerance for double comparison
        if (Math.abs(followerActualX - followerExpectedX) < epsilon) {
            System.out.println("FollowerElement position test passed!");
        } else {
            System.out.println("FollowerElement position test failed!");
            System.out.println("Expected X position: " + followerExpectedX);
            System.out.println("Actual X position: " + followerActualX);
        }
    }
}