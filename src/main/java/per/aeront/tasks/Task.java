package per.aeront.tasks;
import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;

/*
 * A Task is an item on someone's to-do list they wish to complete.
 *
 *
 * ATTRIBUTES:
 * 	  Name (SimpleStringProperty): the name of the task
 *    Description (String) (Optional): An optional description of the task
 *    Due Date (LocalDate): the date the task is due by
 *    Due Time (LocalTime) (Optional): The time the task is due on its due date
 *    Difficulty (int): the task's difficulty, ranging from 1-10
 *    Pre-Requisites (ArrayList of Strings) (Optional): All task names that must be completed
 *                                                    before this task (in the TaskList).
 *    Estimated Duration (int, minutes) (Optional): The estimated duration of this task in minutes
 *    Time Per Sitting (int, minutes) (Optional): The longest time a user would be comfortable
 *                                                working on the task per sitting, default.
 * 
 * METHODS:
 *   Every attribute has a get and set method that has been omitted here.
 *   timeToDue(): Returns how long (in years, months, days, hours, minutes) until task is due.
 *   toCSVRecord(): A helper function that converts this task into CSV format info.
 *   
 */

public class Task {
    private SimpleStringProperty taskName;
    private String description;
	private int difficulty;
	private LocalDate dueDate;
    private LocalTime dueTime;
    private ArrayList<String> prereqs;
    private int estimatedDuration;
    private int timePerSitting;
    
    public Task(String taskName, String description, String difficulty, String dueDate, String dueTime,
                String estDur, String timeSit)
	{
        //Required paramaters
		this.taskName = new SimpleStringProperty(taskName);
        this.dueDate = LocalDate.parse(dueDate);
		this.difficulty = Integer.parseInt(difficulty);
        
        //Optional parameters
        this.description = description;
        
        //Defaults for optional non-String parameters, integers set to -1 so can be avoided
        if ("".equals(dueTime))
        {
          this.dueTime = LocalTime.parse("00:00:00");
        }
        else 
        {
          //concat :00 to end of given time for LocalTime format (ex. 12:23:00)
          this.dueTime = LocalTime.parse(dueTime.concat(":00"));
        }
        if ("".equals(estDur))
        {
          this.estimatedDuration = -1; 
        }
        else {this.estimatedDuration = Integer.parseInt(estDur);}
        if ("".equals(timeSit))
        {
          this.timePerSitting = -1;
        }
        else {this.timePerSitting = Integer.parseInt(timeSit);}
	}
    
    //Alternate constructor to read from a CSV file. Differs in that it reads in prereqs.
    //TODO: Actually read prereqs correctly.
    public Task(String taskName, String description, String difficulty, String dueDate, String dueTime,
                String estDur, String timeSit, String prereqs)
	{
		this.taskName = new SimpleStringProperty(taskName);
        this.description = description;
		this.difficulty = Integer.parseInt(difficulty);
		this.dueDate = LocalDate.parse(dueDate);
        this.dueTime = LocalTime.parse(dueTime);
        //this.prereqs = ?
        this.estimatedDuration = Integer.parseInt(estDur);
        this.timePerSitting = Integer.parseInt(timeSit);
	}
    
    public String getTaskName(){
      return this.taskName.get();
    }
    public void setTaskName(String taskName){
      this.taskName.set(taskName);
    }
    public String getDescription(){
      return this.description;
    }
    public void setDescription(String desc){
      this.description = desc;
    }
    public Integer getDifficulty(){
      return this.difficulty;
    }
    public void setDifficulty(int diff){
      this.difficulty = diff;
    }
    public LocalDate getDueDate(){
      return this.dueDate;
    }
    public void setDueDate(LocalDate due){
      this.dueDate = due;
    }
    public LocalTime getDueTime(){
      return this.dueTime;
    }
    public void setDueTime(LocalTime dueTime){
      this.dueTime = dueTime;
    }
    public void addPrereq(String newName){
      prereqs.add(newName);
    }
    public void removePrereq(String taskName){
      prereqs.remove(taskName);
    }
    public Integer getEstimatedDuration(){
      return this.estimatedDuration;
    }
    public void setEstimatedDuration(int estdur){
      this.estimatedDuration = estdur;
    }
    public Integer getTimePerSitting(){
      return this.timePerSitting;
    }
    public void setTimePerSitting(int tsit){
      this.timePerSitting = tsit;
    }
	
	//Checks remaining time until this task is due, returned as a Duration.
	public Duration timeToDue()
	{
      return Duration.between(LocalDate.now(), this.dueDate);
	}
	
    //Checks whether this task is past due. A wrapper for timeToDue, where
    //TRUE is returned if task is past Due and FALSE otherwise.
    public boolean pastDue()
    {
      return this.timeToDue().isNegative();
        
    }
	
    public String[] toCSVRecord()
    {
      String[] retArray = new String[8];
      retArray[0] = this.getTaskName();
      retArray[1] = this.getDescription();
      retArray[2] = Integer.toString(this.getDifficulty());
      retArray[3] = this.dueDate.toString();
      retArray[4] = this.dueTime.toString();
      retArray[5] = Integer.toString(this.getEstimatedDuration());
      retArray[6] = Integer.toString(this.getTimePerSitting());
      retArray[7] = "prereqs";
      
      return retArray;
    }
    
	//Formats task information in an easy-to-use format for file reading and writing
	//File format (CSV):
	//<Task Name>, <Description>, <diff>, <dueDate>, ...
	public String toFileString() {
		
		StringBuilder str = new StringBuilder(); 
		str.append(this.getTaskName());
		str.append(",");
		str.append(Integer.toString(this.difficulty));
		str.append(",");
		str.append(this.dueDate.toString());
		str.append("\n");
		return str.toString();
	}

}
