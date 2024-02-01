package de.junkerjoerg12.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {// simmilar to JFrame
  public static void main(String[] args) {
    launch(args);

    // everything after this is executed after the window is closed
  }

  @Override
  public void start(Stage mainWindow) throws Exception {
    // Stage stage = new Stage(); // creates a new stage
    // Group root = new Group();// a rootnode
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
    Scene scene = new Scene(root, 199, 245, Color.PINK);// simmilar to JPanel, needs a rootnode as argument

    // adds a logo to the window
    Image logo = new Image(getClass().getResourceAsStream("/de/junkerjoerg12/ui/ExLab.png"));
    mainWindow.getIcons().add(logo);

    mainWindow.setTitle("Expert Laboratory");
    mainWindow.setScene(scene);
    mainWindow.show();
    System.out.println("Hallo, ich bin eine methode");
  }

}