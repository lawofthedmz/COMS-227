package hw1;
/**
 * Balloon is used to model a hot air balloon 
 * @author Murphy Glawe murphg04
 */
public class Balloon {
	
	/*
	*  Instance Variables
	*/
	/** Fuel used to heat air in balloon */
	private double fuelRemaining; 
	/** Mass of the balloon */
	private double mass;
	/** Temperature of air outside of the balloon */
	private double airTemp;
	/** The burn rate of the fuel */
	private double burnRate;
	/**The temperature of the balloon */
	private double balloonTemp ;
	/** Velocity of the balloon */
	private double velocity;
	/** Altitude of the ballon */
	private double altitude;
	/** Length of tether */
	private double tetherLength;
	/** Direction of the wind in degrees*/
	private double windDirection;
	/** Time simulation has been run in seconds */
	private long timeElapsed;
	/** Initial Air temp for reset purposes */
	private double initialAirTemp;
	/** Initial wind direction for reset purposes */
	private double initialWindDirection;
	
	 /*
	 *  Constants for the code
	 */
	/** Heat loss Factor */
	private final double H = 0.1; 
	/** Volume of air in balloon in meters cubed */
	private final double V = 61234.0;
	/** Acceleration due to gravity in meters/seconds squared */
	private final double AG= 9.81;
	/** Gas constant in Joules Per Kilogram Per Kelvin */
	private final double R = 287.05;
	/** Standard pressure in hecto-pascals */
	private final double P = 1013.25;
	/** Kelvin at zero degrees Celsius */
	private final double KC = 273.15;
	
	
	/**
	 * Constructs a new balloon simulation
	 * @param airTemp Temperature of air outside of the balloon chosen by user
	 * @param windDirection Direction of the wind in degrees chosen by user
	 */
	public Balloon(double airTemp, double windDirection) {
		// Sets air temp and winddirection to input
		this.airTemp = airTemp;
		this.windDirection = windDirection;
		// Sets balloon temp and air temp equal to each other
		balloonTemp = airTemp;
		// Make initial variables for reset purposes
		initialWindDirection = windDirection;
		initialAirTemp = airTemp;
		
	}
	
	/**
	 * Gets remaining fuel
	 * @return fuel Current fuel amount
	 */
	public double getFuelRemaining() {
		return fuelRemaining;
	}
		/**
		 * Sets remaining fuel to the current fuel amount
		 * @param fuel Current fuel amount to be set
		 * 
		 */
	public void setFuelRemaning(double fuel) {
		fuelRemaining = fuel;
	}
	/**
	 *  gets mass of balloon 
	 *  @return mass Current Balloon mass
	 */
	public double getBalloonMass() {
		return mass;
	}
	/**
	 * Sets balloon to current mass
	 * @param mass Current Balloon mass to be set
	 */
	public void setBalloonMass(double mass) {
		this.mass = mass;
	}
	/**
	 *  Gets temp of air temperature outside of the balloon 
	 *  @return outsideTemp Current temperature outside of the Balloon
	 */
	public double getOutsideAirTemp() {
		return airTemp;
	}
	/**
	 * Sets the temp outside of the Balloon to temp
	 * @param temp Current temperature outside of the Balloon to be set 
	 */
	public void setOutsideAirTemp(double temp) {
		airTemp = temp;
	}
	/**
	 * Gets fuel burn rate
	 * @return burnRate Current burn rate
	 */
	public double getFuelBurnRate() {
		return burnRate;
	}
	/**
	 *  Sets burn rate 
	 *  @param rate Current burn rate to be set
	 */
	public void setFuelBurnRate(double rate) {
		burnRate = rate; 
	}
	/**
	 *  Gets temperate of the balloon
	 *  @return balloonTemp Current temperature inside of the Balloon
	 */
	public double getBalloonTemp() {
		return balloonTemp;
	}
	/**
	 *  Sets baloon temperature to temp
	 *  @param temp Current temperature inside of the Balloon to be set
	 */
	public void setBalloonTemp(double temp) {
		balloonTemp = temp;
	}
	/**
	 *  Gets velocity of balloon
	 *  @return velocity Current velocity
	 */
	public double getVelocity() {
		return velocity;
	}
	/**
	 *  gets altitude of balloon 
	 *  @return altitude Current altitude
	 */
	public double getAltitude() {
		return altitude;
	}
	/**
	 * Gets length of tether
	 * @return tetherLength Current length of the Balloon tether
	 */
	public double getTetherLength() {
		return tetherLength;
	}
	/**
	 *  Gets the length of the tether - altitude
	 *  @return tetherLength Current length of the Balloon tether after subtracting altitude
	 */
	public double getTetherRemaining() {
		return tetherLength - altitude;
	}
	/**
	 * Sets tether length
	 * @param length Current length of the Balloon tether
	 */
	public void setTetherLength(double length) {
		tetherLength = length;
	}
	/**
	 *  Gets wind direction in degrees
	 *  @return windDirection Current wind direction
	 */
	public double getWindDirection() {
		return windDirection;
	}
	/**
	 * changes wind direction while keeping the direction within range and working with negatives
	 * @param degrees Current wind direction
	 */
	public void changeWindDirection(double degrees) {
		windDirection += degrees;
		windDirection = (windDirection + 360) % 360;
	}
	/**
	 * gets number of full minutes that have passed in simulation 
	 * @return timeElapsed Minutes elapsed
	 */
	public long getMinutes() {
		return timeElapsed / 60; 
	}
    /**
     * gets number of seconds left after minutes
     * @return timeElapsed Seconds Elapsed
     */
	public long getSeconds() {
		return timeElapsed % 60;
	}
	/**
	 *  Updates the simulation and moves the simulation forward by one second. 
	 *  The new burn rate, fuel amount, balloon temperature, velocity, and altitude are calculated in this order. 
	 *  [[The equations used to help calculate the velocity can be found in hw1-2024-spring-2.pdf in Canvas]]
	 */
	public void update() {
		// Adds one second to elapsed time
		timeElapsed++;
		
		// To update velocity::::	
		
		// Sets burn rate to be used in the equations
		burnRate = Math.min(fuelRemaining, burnRate);
		// adjusts fuel 
		fuelRemaining -= Math.min(fuelRemaining, burnRate);
		// Rate of change equation
		double deltaT = burnRate + (airTemp - balloonTemp) * H;	
		// Balloon temp after one second
		balloonTemp += deltaT;
		// Equation for Density of surrounding air in kilograms/meters cubed
		double airDensity = P / (R * (airTemp + KC));
		//Equation for  Density of balloon air in kilograms/meters cubed
		double balloonAirDensity = P / (R * (balloonTemp + KC));
		//  Equation For Force of lift in newtons
		double forceLift = V * (airDensity - balloonAirDensity) * AG;
		// Equation for Force of gravity in newtons
		double forceGravity = mass * AG;
		// Equation for Net force in upward direction in newtons
		double netForce = forceLift - forceGravity;
		// Equation for net acceleration
		double netAcceleration = netForce / mass;
		// Updates velocity in upward direction
		velocity +=  netAcceleration;
		// updates altitude in upward direction
		altitude = Math.max(0, Math.min(tetherLength, altitude + velocity));		
				
	}
	/**
	 * Resets simulation to initial state with initial values
	 */
	public void reset() {
		mass = 0;
		velocity = 0;
		tetherLength = 0;
		fuelRemaining = 0;
		burnRate = 0;
		altitude = 0;
		airTemp  = initialAirTemp;
		balloonTemp = airTemp;
		windDirection = initialWindDirection;
	
		
		

	}
}
