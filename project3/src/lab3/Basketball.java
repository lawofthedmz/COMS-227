package lab3;

public class Basketball {
	
	//initial variables
	/**
	 * Inflation status of the ball
	 */
	private boolean isInflated;
	/**
	 * Diameter of the basketball
	 */
	private double diameter;
	/**
	 * Model of a basketball for use in tests
	 * @param givenDiameter
	 */
	public Basketball(double givenDiameter)
	  {
		isInflated = false;
		diameter = givenDiameter;
	  }
	  /**
	   * Determines wether the basketball can be dribbled
	   * @return isInflated Inflation status of the ball
	   */
	  public boolean isDribbleable()
	  {
	    return isInflated;
	  }
	  /**
	   * Returns diameter of the basketball
	   * @return diameter  Diameter of the basketball
	   */
	  public double getDiameter()
	  {
	    return diameter;
	  }
	  /**
	   * Returns circumference of the basketball 
	   * @return diameter * PI 
	   */
	  public double getCircumference()
	  {
	    return Math.PI * diameter;
	  }
	  /**
	   * Sets isInflated to true
	   */
	  public void inflate()
	  {
		  isInflated = true;
	  }

}
