package de.junkerjoerg12.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Group;
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
    Group group = new Group();// a rootnode
    Scene scene = new Scene(group, Color.LIME);// simmilar to JPanel, needs a rootnode as argument

    Path path = Paths.get("/../../../../resources/de/junkerjoerg12/ui/ExLab.png");
    File file = path.toFile();
    Image logo = new Image(file.toURI().toString());
    System.out.println(logo + "logoo");

    mainWindow.getIcons().add(logo);
    mainWindow.setTitle("Expert Laboratory");
    mainWindow.setScene(scene);
    mainWindow.show();// shows the main window
  }

}