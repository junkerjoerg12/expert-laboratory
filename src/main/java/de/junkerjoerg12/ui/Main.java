package de.junkerjoerg12.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    Scene scene = new Scene(root, 199, 245, Color.PINK);// simmilar to JPanel,
    // needs a rootnode as argument
    scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());

    // adds a logo to the window
    Image logo = new Image(getClass().getResourceAsStream("/de/junkerjoerg12/ui/ExLab.png"));
    mainWindow.getIcons().add(logo);

    mainWindow.setMaximized(true);
    mainWindow.setTitle("Expert Laboratory");
    mainWindow.setScene(scene);

    mainWindow.show();

    for (Node child : root.getChildrenUnmodifiable()) {
      if (child.getId().equals("breadboard")) {
        paintGrid((Pane) child, 20);
      }
    }

  }

  private void paintGrid(Pane breadboard, int distanceOfLines) {
    double verticalLines = breadboard.getWidth() / distanceOfLines;
    double horizontalLines = breadboard.getHeight() / distanceOfLines;
    System.out.println(verticalLines);

    System.out.println(horizontalLines);

    for (int i = 0; i < horizontalLines; i++) {
      Line line = new Line(0, i * distanceOfLines, breadboard.getWidth(), i * distanceOfLines);
      line.setOpacity(0.2);
      breadboard.getChildren().add(line);
    }

    for (int i = 0; i < verticalLines; i++) {
      Line line = new Line(i * distanceOfLines, 0, i * distanceOfLines, breadboard.getHeight());
      line.setOpacity(0.2);
      breadboard.getChildren().add(line);
    }
  }
}
