package per.aeront.schedules;

import java.time.LocalTime;
import per.aeront.tasks.Task;

/**
 * A TimeBlock is an element of a OneDaySchedule describing a task to be
 * accomplished (which may or may not be an actual Task).
 * 
 * ATTRIBUTES:
 *   Name (String): The name of this TimeBlock
 *   Start (LocalTime): The beginning of this TimeBlock
 *   End (LocalTime): The end of this TimeBlock
 *   Task (Task, Optional): The task this time block may be made for.
 * 
 * METHODS:
 *   Every attribute has a get and set method that has been omitted here.
 */
public class TimeBlock {
  
  private String name;
  private LocalTime start;
  private LocalTime end;
  private Task task;
  
  public String getName(){return this.name;}
  public void setName(String name){this.name = name;}
  
  public LocalTime getStart(){return this.start;}
  public void setStart(LocalTime start){this.start = start;}
  
  public LocalTime getEnd(){return this.end;}
  public void setEnd(LocalTime end){this.end = end;}
  
  public Task getTask(){return this.task;}
  public void setTask(Task task){this.task = task;}
}
