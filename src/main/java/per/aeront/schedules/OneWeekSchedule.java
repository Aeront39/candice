package per.aeront.schedules;

import java.time.LocalDate;

/**
 * A OneWeekSchedule is a series of time blocks (which may or may not be Tasks)
 * arranged at different times in a single week. A OneWeekSchedule may be used
 * to define when a user is busy and cannot perform tasks, or can describe
 * the schedule a user has chosen to complete their tasks.
 *
 *
 * ATTRIBUTES:
 * 	  Name (String): The name of this schedule
 *    StartDate (LocalDate): The date of the Sunday that starts the schedule week.
 *    Days (Array of OneDaySchedules): The individual schedule of each day of the week.
 *      [0] (OneDaySchedule): The schedule for Sunday
 *      [1] (OneDaySchedule): The schedule for Monday
 *      etc.
 * 
 * METHODS:
 *   Every attribute has a get and set method that has been omitted here.
 *   
 */

public class OneWeekSchedule {
  private String name;
  private LocalDate startDate;
  private OneDaySchedule[] Days;

  
  
  
}
