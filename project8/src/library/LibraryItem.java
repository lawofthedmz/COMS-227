package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class LibraryItem implements Item {
	/**
	 * Title of this item.
	 */
	private String title;
	
	/**
	   * Patron to whom this item is checked out.  This value is null when not checked out.
	   */
	  private Patron checkedOutTo;
	  
	 /**
	  * Due date for this item.  This value is null when not checked out.
	  */
	 private Date dueDate;  
	 
	 /**
	   * Number of times the item has been renewed for the current patron.
	   */
	  private int renewalCount;
	  
	  private int checkOutDays;
	  
	
	protected void setTitle(String title) {
		this.title = title;
	}
	
	protected void setPatron(Patron patron) {
		checkedOutTo = patron;
	}
	
	protected void setDueDate(Date givenDueDate) {
		dueDate = givenDueDate;
	}
	
	protected void setRenewalCount(int givenRenewalCount) {
		renewalCount = givenRenewalCount;
	}
	
	protected void setCheckoutDays(int count) {
		checkOutDays = count;
	}
	@Override  
	public String getTitle() {
		System.out.println("FUCK THIS");
		return title;
	}
	@Override
	public Patron getPatron() {
		return checkedOutTo;
	}
	
	@Override
	public Date getDueDate() {
		return dueDate;
	}
	
	@Override
	public boolean isCheckedOut()
	 {
	   return dueDate != null;
	 }
	
	@Override
	public int compareTo(Item other)
	 {
	   return title.compareTo(other.getTitle());
	 }
	
	@Override
	  public boolean isOverdue(Date now)
	  {
	    if (!isCheckedOut())
	    {
	      return false;
	    }
	    return now.after(dueDate);
	  }
	
	@Override
	  public void renew(Date now)
	  {
	    if (isCheckedOut() && !isOverdue(now) && renewalCount < 2)
	    {
	      checkOut(checkedOutTo, dueDate);
	      renewalCount += 1;
	    }    
	  }
	
	@Override
	  public void checkIn()
	  {
	    if (isCheckedOut())
	    {
	      checkedOutTo = null;
	      dueDate = null;
	      if(renewalCount <= 2) {
	      renewalCount = 0;
	      }
	    }
	  }
	
	@Override
	  public void checkOut(Patron p, Date now)
	  {
	    if (!isCheckedOut())
	    {
	      
	      // use a GregorianCalendar to figure out the Date corresponding to
	      // midnight, 21 days from now
	      GregorianCalendar cal = new GregorianCalendar();
	      cal.setTime(now);
	      cal.add(Calendar.DAY_OF_YEAR, checkOutDays);
	      
	      // always set to 11:59:59 pm on the day it's due
	      cal.set(Calendar.HOUR_OF_DAY, 23);
	      cal.set(Calendar.MINUTE, 59);
	      cal.set(Calendar.SECOND, 59);     
	      
	      // convert back to Date object
	      dueDate = cal.getTime();
	      
	      checkedOutTo = p;      
	    }
	  }
	
	@Override
	  public double getFine(Date now)
	  {
	    if (isCheckedOut() && isOverdue(now))
	    {
	      // how late is it, in milliseconds
	      double elapsed = now.getTime() - dueDate.getTime();
	      
	      // how late is it, in days
	      int millisPerDay = 24 * 60 * 60 * 1000;
	      int daysLate = (int) Math.ceil(elapsed / millisPerDay);
	      
	      // compute the fine, 25 cents per day
	      return daysLate * .25;
	    }
	    else
	    {
	      return 0;
	    }
	  }
}
