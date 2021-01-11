package per.aeront.javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomePage {

    //buttons
    @FXML private Button closeApplicationButton;
    @FXML private Button goToWeeklyScheduleButton;
    @FXML private Button goToTableViewButton;
    
    //Navigation buttons
    @FXML private void closeApplication()
    {
      Stage stage = (Stage) closeApplicationButton.getScene().getWindow();
      stage.close();
    }
  
    @FXML private void goToTableView() throws IOException {
      App.setRoot("taskTableView");
    }
    
    @FXML private void goToWeeklySchedule() throws IOException {
      Stage stage = (Stage) goToWeeklyScheduleButton.getScene().getWindow();
      stage.setWidth(800);
      stage.setHeight(800);
      App.setRoot("weeklySchedule");
    }
}