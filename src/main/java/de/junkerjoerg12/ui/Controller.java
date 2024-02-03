package de.junkerjoerg12.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Controller /* implements Initializable */ {
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

  private double mouseAnchorX;
  private double mouseAnchorY;

  public void test(Event e) {
    System.out.println("Test executed succsefully");
    System.err.println("Event: " + e);
    System.out.println("Eventtype: " + e.getEventType());
    System.out.println();
  }

  public void clicked(MouseEvent e) {
    System.out.println("clicked");
    System.out.println("e.getSource *= " + e.getSource());
    mouseAnchorX = e.getX();
    mouseAnchorY = e.getY();
  }

  public void dragged(MouseEvent e) {
    System.out.println("dragged");
    System.out.println("e.getSource() = " + e.getSource());
    // System.out.println(e.getSource().getClass());
    Node node = (Node) e.getSource();
    node.setLayoutX(e.getSceneX() - mouseAnchorX);
    node.setLayoutY(e.getSceneY() - mouseAnchorY);

  }

  // @override
  // public void initialize(url location, resourcebundle resources) {
  // // system.out.println(and);
  // // system.out.println(nand);
  // // system.out.println(or);
  // // system.out.println(nor);
  // draggablemaker.makedraggable(nor);
  // draggablemaker.makedraggable(nand);
  // draggablemaker.makedraggable(and);
  // draggablemaker.makedraggable(or);
  // // draggablemaker.makedraggable(rectangel);//node = rectangle[id=rectangel,
  // // x=0.0, y=0.0, width=200.0, height=200.0, fill=0x1e90ffff,
  // stroke=0x000000ff,
  // // strokewidth=1.0]
  // // nothing is draggable yet but at least its not chrashing
  // }

}
