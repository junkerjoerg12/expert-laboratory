package de.junkerjoerg12.expert_laboratory;



import de.junkerjoerg12.expert_laboratory.ui_components.Breadboard;
import de.junkerjoerg12.expert_laboratory.ui_components.ModeBar;
import de.junkerjoerg12.expert_laboratory.ui_components.MyMenuBar;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {// simmilar to JFrame

  public static void main(String[] args) {
    System.out.println("Application starting ...");
    launch(args);
  }
  @Override
  public void start(Stage mainWindow) throws Exception {
    Group root = new Group();// a rootnode
    Scene scene = new Scene(root, 199, 245, Color.WHITE);// simmilar to JPanel,
    // needs a rootnode as argument
    // scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());

    // adds a logo to the window

    mainWindow.setMaximized(true);
    mainWindow.setTitle("Expert Laboratory");
    mainWindow.setScene(scene);
    mainWindow.show();
    //!!!Do Not change the order of the Children!!!
    root.getChildren().add(new MyMenuBar());
    root.getChildren().add(new ModeBar(root));
    root.getChildren().add(new Breadboard(root, 10));
    root.getChildren().add(new LogicGateBar(root));

    Image logo = new Image(getClass().getResourceAsStream("/de/junkerjoerg12/ui/ExLab.png"));
    mainWindow.getIcons().add(logo);

    mainWindow.setResizable(false);

  }


}
