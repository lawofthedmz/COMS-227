package library;


import java.util.Date;

/**
 * A Movie is a library item that can be checked out for 7 days and cannot be renewed.
 * If overdue, the fine is 3.00 plus .50 per day,
 * up to a maximum equal to the item's replacement cost.
 */
public class Movie extends LibraryItem
{
  
  /**
   * Replacement cost for this DVD.
   */
  private double replacementCost;
  
  /**
   * Duration of this DVD, in minutes.
   */
  private int duration;

  /**
   * Constructs a Movie with the given title, replacement cost, and duration.
   * @param givenTitle
   *   title for this item
   * @param givenCost
   *   replacement cost for this item, in dollars
   * @param givenDuration
   *   duration of this item, in minutes
   */
  public Movie(String givenTitle, double givenCost, int givenDuration)
  {
    setTitle(givenTitle);
    setDueDate(null);
    setPatron(null);
    setRenewalCount(100);
    replacementCost = givenCost;
    duration = givenDuration;
    setCheckoutDays(7);
  }
  
  /**
   * Returns the duration of this Movie.
   * @return
   *   duration of this Movie
   */
  public int getDuration()
  {
    return duration;
  }

  @Override
  public double getFine(Date now)
  {
    if (isCheckedOut() && isOverdue(now))
    {
      // how late is it, in milliseconds
      double elapsed = now.getTime() - getDueDate().getTime();
      
      // how late is it, in days
      int millisPerDay = 24 * 60 * 60 * 1000;
      int daysLate = (int) Math.ceil(elapsed / millisPerDay);
      
      // compute the fine
      double fine = 3 + daysLate * .50;
      return Math.min(fine, replacementCost);    }
    else
    {
      return 0;
    }
  }


}
