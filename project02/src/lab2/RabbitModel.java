package lab2;
/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  private int currentPopulation; // TODO - add instance variables as needed
  private int lastYear;
  private int yearBefore;
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
	  lastYear = 1;
	  yearBefore = 0;
	  currentPopulation = lastYear + yearBefore; // sets currentPopulation to 0 rabbits
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
	 
	  yearBefore = lastYear;
	  lastYear += currentPopulation;
	  currentPopulation = lastYear + yearBefore;  // increments populations by 1
	  
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	  lastYear = 1;
	  yearBefore = 0;
	  currentPopulation = lastYear + yearBefore; // resets population back to 0 
	  
  }
}
