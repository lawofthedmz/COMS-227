package library;


import java.util.Date;


/**
 * A Documentary is a library item that can be checked out for 14 days and cannot be renewed.
 * If overdue, the fine is 1.00 per day for the first 5 days and .50 per day thereafter,
 * up to a maximum equal to the item's replacement cost.
 */
public class Documentary extends LibraryItem
{

  /**
   * Replacement cost for this Documentary.
   */
  private double replacementCost;
  
  /**
   * Duration of this Documentary, in minutes.
   */
  private int duration;

  /**
   * Constructs a Documentary with the given title, replacement cost, and duration.
   * @param givenTitle
   *   title for this item
   * @param givenCost
   *   replacement cost for this item, in dollars
   * @param givenDuration
   *   duration of this item, in minutes
   */
  public Documentary(String givenTitle, double givenCost, int givenDuration)
  {
    setTitle(givenTitle);
    setDueDate(null);
    setPatron(null);
    setRenewalCount(100);
    replacementCost = givenCost;
    duration = givenDuration;
    setCheckoutDays(14);
  }

  /**
   * Returns the duration of this Documentary.
   * @return
   *   duration of this Documentary
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
      double fine;
      if (daysLate <= 5)
      {
        fine = daysLate;
      }
      else
      {
        fine = 5 + (daysLate - 5) * .50;
      }
      return Math.min(fine, replacementCost);    }
    else
    {
      return 0;
    }
  }
}