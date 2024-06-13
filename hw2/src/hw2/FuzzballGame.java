package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Murphy Glawe net-id: murphg04
 */
public class FuzzballGame {
  /**
   * Number of strikes causing a player to be out.
   */
  public static final int MAX_STRIKES = 2;

  /**
   * Number of balls causing a player to walk.
   */
  public static final int MAX_BALLS = 5;

  /**
   * Number of outs before the teams switch.
   */
  public static final int MAX_OUTS = 3;
  
  /**
   * Instance Variables
   */
  
  /**
   * Variable that keeps track of whether its top or bottom of the inning 
   */
  private int topInning;
  /**
   * Counts number of balls
   */
  private int balls;
  /**
   * Counts number of strikes
   */
  private int strikes;
  /**
   * Counts number of outs
   */
  private int outs;
  /**
   * First base boolean
   */
  private boolean first;
  /**
   * Second base boolean
   */
  private boolean second;
  /**
   * Third base boolean
   */
  private boolean third; 
  /**
   * Score variable for team 0
   */
  private int score0;
  /**
   * Score variable for team1
   */
  private int score1;
  /**
   * Keeps track of the max number of innings user decide the game can be
   */
  private int maxInnings;
 
  
  // TODO: EVERTHING ELSE
  // Note that this code will not compile until you have put in stubs for all
  // the required methods.
  
  
  /**
   * Constructs a game that has the given number of innings and starts at the top
   * of inning 1
   * @param givenInnings number of innings for the game 
   */
  public FuzzballGame(int givenInnings)
  {
	  first = false;
	  second = false;
	  third = false;
	  topInning = 1;
	  maxInnings = givenInnings;
	  score0 = 0;
	  score1 = 0;
	  
  }
  
  /**
   * Method that implements logic to tell if game is over or not
   * @return true if the game is over, false otherwise
   */
  public boolean gameEnded()
  {
	  if (whichInning() > maxInnings) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Returns true if there is a runner on the indicated base, false otherwise.
   * @param baseNumber - base number to check
   * @return true if there is a runner on the indicated base
   */
  public boolean runnerOnBase(int baseNumber)
  {
	  if (baseNumber == 1) {
		  return first;
	  }  
	  if (baseNumber == 2) {
		  return second;
	  } 
	  if (baseNumber == 3) {
		  return third;
	  } 
	  return false;
  }
  
  /**
   * Returns the current inning. Innings are numbered starting at 1. 
   * When the game is over, this method returns the 
   * game's total number of innings, plus one.
   * @return current inning, or the number of innings plus one in case the game is over
   */
  public int whichInning()
  {
	  int value = (topInning + 1) / 2;
	  return value;
	  
  }
  
  /**
   * Returns true if it's the first half of the inning (team 0 is at bat).   
   * @return true if it's the first half of the inning, false otherwise
   */
  public boolean isTopOfInning()
  {
	  if (topInning % 2 != 0) {
		  return true;
	  } else {
		  return false;
	  }
  }

  /**
   * Returns number of outs for the team currently at bat
   * @return number of outs
   */
  public int getCurrentOuts()
  {
	  return outs;
  }

  /**
   * Returns the count of balls for the current batter
   * @return current number of balls
   */
  public int getBallCount()
  {
	  return balls;
  }
  
  /**
   * Returns the number of called strikes for the current batter
   * @return current number of strikes 
   */
  public int getCalledStrikes()
  {
	  return strikes;
  }
  
  /**
   * Returns the score for team 0
   * @return score for team 0 
   */
  public int getTeam0Score()
  {
	  return score0;
  }
  
  /**
   * Returns the score for team 1
   * @return score for team 1 
   */
  public int getTeam1Score()
  {
	  return score1;
  }
  
  /**
   * Method called to indicate a strike for the current batter, if swung parameter is true the batter is immediately out
   * @param swung true if the batter swung at the pitch, false if it is called a strike
   */
  public void strike(boolean swung)
  {	  if (gameEnded()) {
	  	return;
      }
	  if (swung) {
		  outs++;
		  newBatter();

	  } else {
		  strikes++;
	  }
	  if (strikes == MAX_STRIKES) {
		  outs++;
		  newBatter();

	  }
	  if (outs == MAX_OUTS) {
		  topInning += 1;
		  outs = 0;
		  strikes = 0;
		  balls = 0;
		  first = false;
		  second = false;
		  third = false;
	  } 
  }
  
  /**
   * Method called to indicate that the batter is out due to a caught fly
   */
  public void caughtFly()
  {
	  if (gameEnded()) {
		  	return;
	  }
	  outs++;
	  newBatter();
	  if (outs == MAX_OUTS) {
		  topInning += 1;
		  outs = 0;
		  first = false;
		  second = false;
		  third = false;
	  }
  }
  
  /**
   * Method called to indicate a bad pitch at which the batter did not swing 
   * This method add 1 to the batter's count of balls, possibly resulting in a walk
   */
  public void ball()
  {
	  if (gameEnded()) {
		  	return;
	      }
	   balls++;
	   if (balls == MAX_BALLS) {
		   newBatter();
		   shiftRunnersForWalk();
	   }
	 
  }
  
  /**
   * Method called to indicate that the batter hit the ball. The interpretation of the distance parameter is as follows:
   * less than 15: the hit is a foul and the batter is immediately out.
   * 
   * at least 15, but less than 150: the hit is a single. An imaginary runner advances to first base, and all other runners on base advance by 1. 
   * If there was a runner on third base, the score increases by 1.
   * 
   * at least 150, but less than 200: the hit is a double. An imaginary runner advances to second base, and all other runners on base advance by 2. 
   * If there were runners on second or third, the score increases by 1 or 2.
   * 
   * at least 200, but less than 250: the hit is a triple. An imaginary runner advances to third base, and all other runners on base advance by 3. 
   * If there were runners on first, second, or third, the score is increased by 1, 2, or 3.
   * 
   *at least 250: the hit is a home run. All imaginary runners currently on base advance to home. The score is increased by 1 plus the number of runners on base. 
   * 
   * @param distance - distance the ball travels (possibly negative)
   */
  public void hit(int distance)
  {
	  if (gameEnded()) {
		  	return;
	      }  
	if (distance < 15) {
		newBatter();
		outs++;
		if (outs == MAX_OUTS) {
			  topInning += 1;
			  outs = 0;
			  first = false;
			  second = false;
			  third = false;
		  }
	} else if (distance < 150) {
		newBatter();
		shiftRunners(1); // single
	} else if (distance < 200) {
		newBatter();
		shiftRunners(2); // double 
	} else if (distance < 250) {
		newBatter();
		shiftRunners(3); // triple 
	} else {
		newBatter();
		shiftRunners(4); // homerun
	}
  }
  
  
  /**
   * HELPER METHODS
   */
  
  /**
   * Updates runners after hit
   * @param basesReached
   */
  private void shiftRunners(int basesReached) 
  {
	  if (basesReached == 1) {
		  if (third) {
			  updateScore();
			  third = false;
		  }
		  if (second) {
			  third = true;
			  second = false;
		  }
		  if (first) {
			  second = true;
			  first = false;
		  }
		  first = true;  
	  } else if (basesReached == 2) {
		  if (third) {
			  updateScore();
			  third = false;
		  }
		  if (second) {
			  updateScore();
			  second = false;
		  }
		  if (first) {
			  third = true;
			  first = false;
		  }
		  second = true;
	  } else if (basesReached == 3) {
		  if (third) {
			  updateScore();
			  third = false;
		  }
		  if (second) {
			  updateScore();
			  second = false;
		  }
		  if (first) {
			  updateScore();
			  first = false;
		  }
		  third = true;
	  } else {
		  if (third) {
			  updateScore();
			  third = false;
		  }
		  if (second) {
			  updateScore();
			  second = false;
		  }
		  if (first) {
			  updateScore();
			  first = false;
		  }
		  updateScore();
	}
	  
  }
  
  /**
   * Resets strikes and balls for new batter 
   */
  private void newBatter() {
	  balls = 0;
	  strikes = 0;
  }
  
  /**
   * Method to shift runners if a batter gets walked
   */
  private void shiftRunnersForWalk() {
	  newBatter();
	  if (first && second && third) {
		  updateScore();
	  } else if (second && first) {
		  third = true;
	  } else if (first) {
		  second = true;
	  } else { 
		  first = true;
	  }
	  
  }
  
  /**
   * Method that is called to add point for proper team depending in top or bottom of inning
   */
  private void updateScore() {
	  if (isTopOfInning()) {
		  score0++;
	  } else {
		  score1++;
	  }
  }
  
  
  
  // The methods below are provided for you and you should not modify them.
  // The compile errors will go away after you have written stubs for the
  // rest of the API methods.
  /**
   * Returns a three-character string representing the players on base, in the
   * order first, second, and third, where 'X' indicates a player is present and
   * 'o' indicates no player. For example, the string "oXX" means that there are
   * players on second and third but not on first.
   * 
   * @return three-character string showing players on base
   */
  public String getBases()
  {
    return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o")
        + (runnerOnBase(3) ? "X" : "o");
  }

  /**
   * Returns a one-line string representation of the current game state. The
   * format is:
   * <pre>
   *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
   * </pre>
   * The first three characters represent the players on base as returned by the
   * <code>getBases()</code> method. The 'T' after the inning number indicates
   * it's the top of the inning, and a 'B' would indicate the bottom. The score always
   * shows team 0 first.
   * 
   * @return a single line string representation of the state of the game
   */
  public String toString()
  {
    String bases = getBases();
    String topOrBottom = (isTopOfInning() ? "T" : "B");
    String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
    return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(),
        getTeam1Score(), getBallCount(), getCalledStrikes(), getCurrentOuts());
  }
}
