package library;

import java.util.Date;

/**
 * A ReferenceItem is a library item that cannot be checked out.
 */
public class ReferenceItem extends LibraryItem {
  
  /**
   * Constructs a ReferenceItem with the given title.
   * @param givenTitle
   */
  public ReferenceItem(String givenTitle)
  {
		setTitle(givenTitle);
		setDueDate(null);
		setPatron(null);
		setRenewalCount(100);
		}
  
  
  @Override
  public void checkOut(Patron p, Date now)
  {
    // can't be checked out
  }

}
