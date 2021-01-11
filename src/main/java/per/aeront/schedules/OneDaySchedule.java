package per.aeront.schedules;

import java.util.ArrayList;

/**
 * A OneDaySchedule is a set of timeblocks (which may or may not be Tasks)
 * describing when during a 24 hour day the user is occupied or busy.
 * Primarily used as a sub-component of a OneWeekSchedule.
 *
 *
 * ATTRIBUTES:
 * 	  Name (String): The name of this schedule
 *    timeBlocks (ArrayList of TimeBlocks): The individual timeblocks that
 *                                          make up this day's schedule.
 * 
 * METHODS:
 *   Every attribute has a get and set method that has been omitted here.
 *   sortedInsert(TimeBlock tb): inserts a given TimeBlock tb into this
 *                               one day schedule. If a collision occurs,
 *                               returns the name of the TimeBlock it collided with.
 *   
 */

class OneDaySchedule {
  
  private ArrayList<TimeBlock> timeBlocks = new ArrayList<>();
  
  //Inserts a time block into a schedule
  public String sortedInsert(TimeBlock tb)
  {
    //Get length of current timeBlocks
    int n = this.timeBlocks.size();
    
    //If n=0, do direct insert.
    if(n == 0)
    {
      this.timeBlocks.add(tb);
      return("done");
    }
    //Otherwise, begin recursion
    int err = sortedInsert(tb, 0, n);
    if(err != 1)
    {
      return "an error occurred during insertion";
    }
    else
    {
      return "done";
    }
  }
  
  //A recursive version of sortedInsert to hanle the binary insertion.
  public int sortedInsert(TimeBlock tb, int start, int length)
  {
    //Check size of interval: if 1, do an insert.
    
      //Check if before or after that start time and insert.
    
      //Determine if this TimeBlock's start comes before the previous TimeBLock's end
      //or if this TimeBlock's end comes after the next Timeblock's start. If either
      //is the case, cancel insert and return the index of the collided TimeBlock.
    
    //Otherwise, check the start + length/2th element's start time to determine
    //which half of range must be checked next.
    
    
    
    return 1;
  }
  
  
  
}
