package lab2;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel4
{
  private int currentPopulation; // TODO - add instance variables as needed
  Random random = new Random();	
  
  /*
   * Constructs a new RabbitModel.
   */
  public RabbitModel4()
  {
	  
	  currentPopulation = 0;// sets currentPopulation to 0 rabbits
    // TODO 
	
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return currentPopulation;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    // TODO
	 
	  currentPopulation += random.nextInt(10);  // increments populations by 1
	  
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	  currentPopulation = 0; // resets population back to 0 
	  
  }
}
