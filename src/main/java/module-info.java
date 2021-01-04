module per.aeront {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens per.aeront.javafx;
    opens per.aeront.tasks;
    exports per.aeront.javafx;
  requires opencsv;
}