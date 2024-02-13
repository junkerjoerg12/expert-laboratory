package de.junkerjoerg12.expert_laboratory.logicGates;

import java.util.ArrayList;

import de.junkerjoerg12.expert_laboratory.ui_components.Breadboard;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class LogicGate extends StackPane {
  protected ArrayList<Boolean> inputs;
  protected boolean output;

  protected final double WIDTH = 100;

  protected double mouseAnchorX;
  protected double mouseAnchorY;

  protected Breadboard breadboard;

  protected Rectangle rect;

  public LogicGate() {
    inputs = new ArrayList<>(2);
    this.setStyle("-fx-background-color: pink;");

    addRectangel();
    setPrefSize(WIDTH, 100);

    setOnMouseReleased(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        mouseAnchorX = 0;
        mouseAnchorY = 0;
      }
    });

    setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        // Node node = (Node) e.getSource();

        if (mouseAnchorX == 0 && mouseAnchorY == 0) {
          mouseAnchorX = e.getX();
          mouseAnchorY = e.getY();
        }

        double distanceOfLines = getDistanceOfLInes();

        // node.setLayoutX((e.getScreenX() /*+ mouseAnchorX*/) - ((e.getScreenX() /*+
        // mouseAnchorX*/) % distanceOfLines));
        // node.setLayoutY((e.getScreenY() /*+ mouseAnchorY*/) - ((e.getScreenY()/* +
        // mouseAnchorY*/) % distanceOfLines));
        System.out.println("getWidth of parent" + getParent().getBoundsInLocal().getWidth());

        if (getLayoutX() > getParent().getLayoutX() + getParent().getBoundsInLocal().getWidth() && getLayoutY() > getParent().getLayoutY()) {
          setAllOpacity(1.0);
          // node.setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX
          //     - (e.getScreenX() - getParent().getLayoutX()) % distanceOfLines);
          // node.setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY
          //     - (e.getScreenY() - getParent().getLayoutY()) % distanceOfLines);
          setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
          setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);

        } else {
          setAllOpacity(0.5);
          setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
          setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);
        }
      }
    });

  }

  public LogicGate(byte inputs) {
    this.inputs = new ArrayList<>(inputs);
    setPrefSize(WIDTH, 50 * inputs);

  }

  private void addRectangel() {

    Rectangle rect = new Rectangle();
    this.rect = rect;
    // set its background color to a light blue
    rect.setStyle("-fx-background-color: lightblue;");
    rect.setWidth(50);
    rect.setHeight(WIDTH - 30);
    // place the rectangle in the center of the pane
    rect.setLayoutX(getPrefWidth() / 2 - rect.getWidth() / 2);
    rect.setLayoutY(getPrefHeight() / 2 - rect.getHeight() / 2);

    // add the rectangle to the pane
    getChildren().add(rect);
  }

  public void setAllVisibility(boolean visible) {
    this.setVisible(visible);
    for (Node child : this.getChildren()) {
      child.setVisible(visible);
    }
  }

  public void setAllOpacity(double opacity) {
    this.setOpacity(opacity);
    for (Node child : this.getChildren()) {
      child.setOpacity(opacity);
    }
  }

  public static int getFutureWidth() {
    return 100;
  }

  private double getDistanceOfLInes() {
    if (breadboard == null) {
      for (Node child : getParent().getParent().getChildrenUnmodifiable()) {
        if (child.getId().equals("breadboard")) {
          this.breadboard = (Breadboard) child;
        }
      }

    }
    for (int i = 0; i < breadboard.getChildren().size(); i++) {
      if (breadboard.getChildren().get(i).getId() == null && breadboard.getChildren().get(i + 1).getId() == null) {
        Line line = (Line) breadboard.getChildren().get(i);
        if (line.getStartX() == line.getEndX()) {
          Line line1 = (Line) breadboard.getChildren().get(i);
          Line line2 = (Line) breadboard.getChildren().get(i + 1);
          return line1.getStartX() - line2.getStartX();
        } else {
          Line line1 = (Line) breadboard.getChildren().get(i);
          Line line2 = (Line) breadboard.getChildren().get(i + 1);
          return line1.getStartY() - line2.getStartY();
        }
      } else {
        System.err.println("look over the hieraarchy of the children from breadboard");
      }
    }
    return -1;
  }
}