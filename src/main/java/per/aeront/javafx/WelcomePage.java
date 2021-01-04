package per.aeront.javafx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomePage {

    @FXML
    //button for exiting program
    private Button closeApplicationButton;
    
    @FXML
    //button for exiting program
    private Button goToTableViewButton;
    
    @FXML
    //Method for the exitAppButton
    private void closeApplication()
    {
      Stage stage = (Stage) closeApplicationButton.getScene().getWindow();
      stage.close();
    }
  
    @FXML
    private void goToTableView() throws IOException {
        App.setRoot("taskTableView");
    }
}