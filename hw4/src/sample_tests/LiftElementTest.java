package sample_tests;

import org.junit.jupiter.api.Test;

import hw4.FollowerElement;
import hw4.LiftElement;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

public class LiftElementTest {

    @Test
    public void testLiftElementNotOnLiftWhenMoving() {
        // Arrange
        LiftElement lift = new LiftElement(300, 200, 30 * 3, 30 / 2);
        lift.setBounds(100, 200); // vertical motion bounds
        lift.setVelocity(0, 3); // initial velocity

        FollowerElement follower = new FollowerElement(30, 30, 0);
        follower.setColor(Color.RED);
        follower.setVelocity(1, 0);
        lift.addAssociated(follower);

        // Act
        lift.update();

        // Assert
        assertFalse(lift.getAssociated().contains(follower), "FollowerElement should not be on the lift when it is moving");
    }
}
