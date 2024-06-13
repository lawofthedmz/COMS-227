package lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import balloon4.Balloon;
/*
 * Balloon 1 Error: when going over max radius balloon radius does not reset 
 * Balloon 2 Error: when testing balloon when popped and blowing air into it the ballon should return 0 because it is still popped but that did not work
 * Balloon 3 Error: It appears that the blow method only sets radius to the variable listed instead of adding to the radius
 * Balloon 4 Error: When balloon is deflated it also pops the balloon 
 * 
 */
/*
 * A newly constructed Balloon should have radius zero.
   A newly constructed Balloon should not be popped.
   After calling blow(5) on a Balloon with maximum radius 10, the radius should be 5.
 */
public class BalloonTests
{
   
	// margin of error for floating-point comparisons
    
    
    private Balloon b;

    @Before    
    public void setup()  // runs before every test case
    {
      b = new Balloon(10);
      
    }
    
    @Test
    public void testInitial()
    {
      assertEquals(false, b.isPopped());
      assertEquals(0, b.getRadius());
    }


    @Test
    public void testAfterBlow1()
    {
      b.blow(5);
      assertEquals(5, b.getRadius());
      assertEquals(false, b.isPopped());
    }

    @Test
    public void testAfterDeflation()
    {
      b.deflate();
      assertEquals(0, b.getRadius());
      assertEquals(false, b.isPopped());

    }
    
    @Test
    public void testAfterBlow2()
    {
      b.blow(5);
      b.blow(5);
      assertEquals(10, b.getRadius());
      assertEquals(false, b.isPopped());

    }
    
    @Test
    public void testRadiusAfterBlowOverMax()
    {
      b.blow(15);
      b.isPopped();
      assertEquals(0, b.getRadius());

     
      
    }
    @Test
    public void testAfterPop()
    {
    	
      b.pop();
      b.blow(5);
      assertEquals(0, b.getRadius());
      assertEquals(true, b.isPopped());

    }
    
    
}