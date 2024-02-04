package de.junkerjoerg12.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Controller {
  @FXML
  private StackPane nand;
  @FXML
  private StackPane and;
  @FXML
  private StackPane or;
  @FXML
  private StackPane nor;
  @FXML
  private Rectangle testRect;
  @FXML
  private Pane breadboard;

  private double mouseAnchorX;
  private double mouseAnchorY;

  public void test(Event e) {
    System.out.println("Test executed succsefully");
    System.err.println("Event: " + e);
    System.out.println("Eventtype: " + e.getEventType());
    System.out.println();
  }

  public void clicked(MouseEvent e) {
    Node node = (Node) e.getSource();
    mouseAnchorX = node.getLayoutX() - e.getScreenX();
    mouseAnchorY = node.getLayoutY() - e.getScreenY();
  }

  public void dragged(MouseEvent e) {
    // "junps up" the hight of the mouse pointer
    Node node = (Node) e.getSource();
    int x;
    int y;

    double distanceOfLines = 0;

    // claculates the distance between the lines in the backgroundgrid, probably no
    // need to calculate everytime
    for (int i = 0; i < breadboard.getChildren().size(); i++) {
      if (breadboard.getChildren().get(i).getId() == null && breadboard.getChildren().get(i + 1).getId() == null) {
        Line line = (Line) breadboard.getChildren().get(i);
        if (line.getStartX() == line.getEndX()) {
          Line line1 = (Line) breadboard.getChildren().get(i);
          Line line2 = (Line) breadboard.getChildren().get(i + 1);
          distanceOfLines = line1.getStartX() - line2.getStartX();
          break;
        } else {
          Line line1 = (Line) breadboard.getChildren().get(i);
          Line line2 = (Line) breadboard.getChildren().get(i + 1);
          distanceOfLines = line1.getStartY() - line2.getStartY();
          break;
        }
      } else {
        System.err.println("look over the hieraarchy of the children from breadboard");
      }
    }

    node.setLayoutX((e.getSceneX() + mouseAnchorX) - ((e.getSceneX() + mouseAnchorX) % distanceOfLines));
    node.setLayoutY((e.getSceneY() + mouseAnchorY) - ((e.getSceneY() + mouseAnchorY) % distanceOfLines));
  }

}
