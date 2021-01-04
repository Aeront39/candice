package per.aeront.javafx;


//import task tools
import per.aeront.tasks.*;

//Import JavaFX packages
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//Import necessary constants
import static per.aeront.javafx.Constants.*;

/*
*This controls the main TaskView page. It imports the tasks from the database
*at initialization, allows new tasks to be added, and old tasks deleted, and
*has a save key to save the current database.
*/

public class TaskTableView implements Initializable{
  
    private TaskList displayTasks = new TaskList();

    //buttons for adding new tasks and exiting
    @FXML private Button newTaskButton;
    @FXML private Button quitTableViewButton;
    
    //table to display tasks
    @FXML private TableView<Task> taskTable;
    
    //Selection model to handle displaying expanded task data
    @FXML private TableViewSelectionModel taskTableSelectionModel;
    
    //Below are the columns for the table
    @FXML private TableColumn<Task, String> colTaskName;
    @FXML private TableColumn<Task, Integer> colTaskDiff;
    @FXML private TableColumn<Task, LocalDate> colTaskDue;
    
    //labels for displaying task data
    @FXML private Label taskNameLabel;
    @FXML private Label taskDifficultyLabel;
    @FXML private Label taskDueDateLabel;
    @FXML private Label taskDescriptionLabel;
    @FXML private Label taskDueTimeLabel;
    @FXML private Label taskEstDurLabel;
    @FXML private Label taskTimeSitLabel;
    @FXML private Label taskPrereqsLabel;
    
    //Create an ObservableList Array to handle the rows which is populated
    //during initialization
    ObservableList<Task> taskData = FXCollections.observableArrayList();
    
    //When a user clicks a row, opens up that task's info in the lower screen
    @FXML private void openTaskInfo()
    {
      Task inTask = (Task) taskTableSelectionModel.getSelectedItems().get(0);
      taskNameLabel.setText(inTask.getTaskName());
      taskDifficultyLabel.setText(Integer.toString((inTask.getDifficulty())));
      taskDueDateLabel.setText(inTask.getDueDate().toString());
      taskDescriptionLabel.setText(inTask.getDescription());
      
      //Check optional variables before updating
      String dueTime = inTask.getDueTime().toString();
      if (dueTime.equals(Constants.DEFAULT_TIME))
      {
        dueTime = Constants.DEFAULT_TASK_VAR_STRING;
      }
      taskDueTimeLabel.setText(dueTime);
      
      String estDur;
      if (inTask.getEstimatedDuration() == Constants.DEFAULT_INT)
      {
        estDur = Constants.DEFAULT_TASK_VAR_STRING;
      }
      else
      {
        estDur = Integer.toString(inTask.getEstimatedDuration());
      }
      taskEstDurLabel.setText(estDur);
      
      String perSit;
      if (inTask.getTimePerSitting() == Constants.DEFAULT_INT)
      {
        perSit = Constants.DEFAULT_TASK_VAR_STRING;
      }
      else
      {
        perSit = Integer.toString(inTask.getTimePerSitting());
      }
      taskEstDurLabel.setText(perSit);
      
      
    }
    
    //Navigate to welcome screen
    @FXML
    private void goToWelcome() throws IOException 
    {
      //Return to title page
      App.setRoot("welcomePage");
    }
    
    
    //Navigate to TaskTableAdd
    @FXML
    private void goToTaskTableAdd() throws IOException{
        //Return to title page
        App.setRoot("taskTableAdd");
    }
    
    //Initialize table data
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
        displayTasks.makeFromCSV(CSVFILENAME);
        //displayTasks = reader.readCSVInput(CSVFileName);
      }
      catch (IOException error)
      {
        System.out.print("Error reading in tasks: cannot read ");
        System.out.println(CSVFILENAME);
        System.out.println(System.getProperty("user.dir"));

      }
      
      //Write all tasks into table
      taskData = getAllTasks(displayTasks);
      taskTable.setItems(taskData);
      
      //Once done, instantiate selection model
      taskTableSelectionModel = taskTable.getSelectionModel();
      
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
    
    //ARCHIVE: Moving content from one Scene to another
    /*
    //Open up the expanded information for a selected event
    @FXML
    private void openInfo(MouseEvent event)
    {
      //method gets a list of selected objects. Since we only ever select one,
      //take the first via indexing. Need to cast since returns generic Object.
      Task task = (Task) taskTableSelectionModel.getSelectedItems().get(0);
      
      try {
        //TODO: Import data between scenes without opening a new window
        //App.setRoot()
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("per/aeront/javafx/taskTableTaskInfo.fxml"));
        Parent root = loader.load();
        
        //Get controller of scene2
        TaskTableTaskInfo infoController = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        infoController.transferTask(task);

        //Show scene 2 in new window            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      } 
      catch (IOException ex)
      {
        
      }
    }*/
}
