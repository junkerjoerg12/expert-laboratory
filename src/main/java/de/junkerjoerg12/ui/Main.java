package de.junkerjoerg12.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    System.out.println("Hallo");
    launch(args);
  }

  @Override
  public void start(Stage mainWindow) throws Exception {
    mainWindow.show();
  }

}