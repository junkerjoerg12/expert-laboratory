package de.junkerjoerg12.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    // "junps up" the hight of the mouse pointer, don't know why
    Node node = (Node) e.getSource();
    node.setLayoutX(e.getSceneX() + mouseAnchorX);
    node.setLayoutY(e.getSceneY() + mouseAnchorY);
  }

}
