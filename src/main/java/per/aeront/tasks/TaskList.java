package per.aeront.tasks;
import com.opencsv.CSVReader;
import java.io.FileWriter;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;

/*
 * The TaskList is an ArrayList containing all the tasks currently in memory. It also handles reading and writing
 * the list to and from memory.
 * ATTRIBUTES:
 * 	  TaskListName (SimpleStringProperty): the name of the Task List.
 *    Tasks (HashMap of Tasks keyed by name): the Tasks assigned to this Task List.
 *    Description (String) (Optional): a description of this Task List.
 * 
 * METHODS:
 *   Every attribute has a get and set method that has been omitted here.
 *   addTask(Task task): add a task to this Task List.
 *   removeTask(String taskName): removes a task with the given name.
 *   makeFromCSV(string filename): populates an (empty) Task List from a local file
 *                                 of the given name.
 *   saveAsCSV(string filename): creates a new CSV at a local location with a given name
 *                               with this Task List's contents.
 *   
 */

public class TaskList {
	
  //TODO: Make private and add method for getting all tasks.
	private HashMap<String, Task> tasks;
	private SimpleStringProperty taskListName;
    private String description;
    
	public TaskList(String name, String desc)
	{
      this.taskListName = new SimpleStringProperty(name);
      this.description = desc;
	  this.tasks = new HashMap<String, Task>();
	}
    
    public TaskList()
	{
      this.taskListName = new SimpleStringProperty("");
      this.description = "";
	  this.tasks = new HashMap<String, Task>();
	}
	
    public String getTaskListName(){
      return this.taskListName.get();
    }
    public void setTaskListName(String name){
      this.taskListName.set(name);
    }
    public String getDescription(){
      return this.description;
    }
    public void setDescription(String description){
      this.description = description;
    }
	public void addTask(Task newTask)
	{
      //Don't add if there's already a task with the same name
      //TODO: Make an error prompt appear.
      if(!this.tasks.containsKey(newTask.getTaskName()))
      {
        this.tasks.put(newTask.getTaskName(), newTask);
      }
	}
	//Removes a given task from the TaskList.
	public void removeTask(String delTaskName)
	{
      this.tasks.remove(delTaskName);
      System.out.print("Successfuly removed task ");
      System.out.println(delTaskName);
		
	}
    public Collection<Task> getAllTasks()
    {
      return this.tasks.values();
    }
    
    public void makeFromCSV(String filename) throws IOException
    {
      CSVReader reader = new CSVReader(new FileReader(filename));
      StringBuffer buffer = new StringBuffer();
      String[] record;
      while ((record = reader.readNext()) != null){
        Task newtask = new Task(record[0],record[1],record[2],record[3],
                                record[4],record[5],record[6],record[7]);
        this.addTask(newtask);
      }
    }
    
    public void saveAsCSV(String filename) throws Exception
    {
      CSVWriter writer = new CSVWriter(new FileWriter(filename));
      
      //Write each task individually using the toCSVList method of each Task.
      for(Task task : this.tasks.values())
      {
        writer.writeNext(task.toCSVRecord());
      }
      
      //Flush the writer at end
      writer.flush();
      
    }

	//Adds entries to task list given a formatted string.
	public void constructFromString(String rawText)
	{
		//Split on new line to get each row
		//String [] rows = rawText.split("\\n");
		
		//String [] row;
		//Create a task for each row
		//for(int i = 0; i < rows.length;i++)
		//{
		//	row = rows[i].split(",");
	//		this.addTask(new Task(row[0], Integer.parseInt(row[1]), LocalDate.parse(row[2])));
			
		//}

	}
	
}