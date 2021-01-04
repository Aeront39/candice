package per.aeront.javafx;


//import task tools
import per.aeront.tasks.*;

//Import JavaFX packages
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/*
*This controls the main TaskView page. It imports the tasks from the database
*at initialization, allows new tasks to be added, and old tasks deleted, and
*has a save key to save the current database.
*/

public class TaskTableAdd implements Initializable{
  
    private TaskList displayTasks = new TaskList();
    
    String CSVFileName = "data/testTasks2.csv";
  
    @FXML private TextField taskName;
    @FXML private DatePicker taskDate;
    @FXML private TextField taskDiff;
    @FXML private TextField taskTime;
    @FXML private TextArea taskDesc;
    @FXML private TextField taskDur;
    @FXML private TextField taskMaxSitting;

    //button for adding new tasks
    @FXML private Button addTaskButton;
    
    //button for quitting the add action
    @FXML private Button quitAddTaskButton;
    
    //table to display tasks
    @FXML private TableView<Task> taskTable;
    
    //Below are the columns for the table
    @FXML private TableColumn<Task, String> colTaskName;
    @FXML private TableColumn<Task, Integer> colTaskDiff;
    @FXML private TableColumn<Task, LocalDate> colTaskDue;
    
    //Create an ObservableList Array to handle the rows which is populated
    //during initialization
    ObservableList<Task> taskData = FXCollections.observableArrayList();
    
    //Adds a given task to the table
    @FXML
    private void addTaskToView(ActionEvent event) 
    {
      String newName = taskName.getText();
      String newDate  = taskDate.getValue().toString();
      String newDiff = taskDiff.getText();
      String newTime = taskTime.getText();
      String newDesc = taskDesc.getText();
      String newDur = taskDur.getText();
      String newMaxSitting = taskMaxSitting.getText();
      
      //Cancel add if any required field is empty
      if ("".equals(newName) || "".equals(newDate) || "".equals(newDiff))
      {
        return;
      }

      //Add the task to the tasklist
      Task newTask = new Task(newName,newDesc,newDiff,newDate,newTime,newDur,newMaxSitting);
      displayTasks.addTask(newTask);
      taskData.add(newTask);
      
      //Once task is added, return to view.
      try {
        goToView();
      } catch (Exception ex) {}
    }
    
    @FXML
    private void goToView() throws Exception {
      
        //Save task list before moving
        displayTasks.saveAsCSV(CSVFileName);
      
        //Return to title page
        App.setRoot("taskTableView");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      //set up columns in the table
      //TODO: Expand to new task values!
      colTaskName.setCellValueFactory(
                new PropertyValueFactory<Task, String>("taskName"));
      colTaskDue.setCellValueFactory(
                new PropertyValueFactory<Task, LocalDate>("dueDate"));
      colTaskDiff.setCellValueFactory(
                new PropertyValueFactory<Task, Integer>("difficulty"));
      
      //Read in tasks from file, saved as a TaskList displayTasks.
      
      try
      {
        displayTasks.makeFromCSV(CSVFileName);
        //displayTasks = reader.readCSVInput(CSVFileName);
      }
      catch (IOException error)
      {
        System.out.print("Error reading in tasks: cannot read ");
        System.out.println(CSVFileName);
        System.out.println(System.getProperty("user.dir"));

      }
      
      //Write all tasks into table
      taskData = getAllTasks(displayTasks);
      taskTable.setItems(taskData);
    }
    
    //Creates observable list from a given TaskList.
    public ObservableList<Task> getAllTasks(TaskList readinTasks)
    {
      
      ObservableList<Task> tasks = FXCollections.observableArrayList();
      for(Task task: readinTasks.getAllTasks())
      {

        tasks.add(task);
      }
      
      return tasks;
    }
}
