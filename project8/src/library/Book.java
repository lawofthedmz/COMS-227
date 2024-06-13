package library;

/**
 * A Book is a library item that can be checked out for 21 days and renewed at most twice.
 * If overdue, the fine is .25 per day.
 */
public class Book extends LibraryItem
{
  
  /**
   * Constructs a book with the given title.
   * @param givenTitle
   */
  public Book(String givenTitle)
  {
    setTitle(givenTitle);
    setDueDate(null);
    setPatron(null);
    setRenewalCount(0);
    setCheckoutDays(21);
    
    
    //absItem item = absItem(title, dueDate, checkedOutTo, renewalCount, -1, -1);
  }
}