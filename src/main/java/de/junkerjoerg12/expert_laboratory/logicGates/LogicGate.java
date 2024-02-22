package de.junkerjoerg12.expert_laboratory.logicGates;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.junkerjoerg12.expert_laboratory.ui_components.Breadboard;
import de.junkerjoerg12.expert_laboratory.ui_components.LogicGateBar;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public abstract class LogicGate extends StackPane {
  protected ArrayList<Boolean> inputs;
  protected boolean output;

  protected final double WIDTH = 100;

  protected double mouseAnchorX;
  protected double mouseAnchorY;

  protected Breadboard breadboard;
  protected LogicGateBar logicGateBar;

  protected Rectangle rect;

  protected LogicGate clonedGate;
  protected LogicGate thisGate;

  // Maybe copy the Gate allways!!

  public LogicGate() {
    // inputs = new ArrayList<>(2);
    this.setStyle("-fx-background-color: pink;");
    // addRectangel();
    setPrefSize(WIDTH, 100);

    setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        // System.out.println("mouseReleased");
        // System.out.println("this gate: " + thisGate + " this gates parent :" +
        // thisGate.getParent());

        // System.out.print("clonedGate: " + clonedGate);
        thisGate.setAllVisibility(true);
        if (thisGate.getParent().getId().equals("breadboard")) {
          breadboard.getChildren().remove(thisGate);
          System.out.println("removing this gate form Breadboard");
        }
        if (clonedGate.getParent().getId().equals("logicGateBar")) {
          logicGateBar.getChildren().remove(clonedGate);
          clonedGate = null;
          System.out.println("removing cloned gate from LogicGateBar");
        }
        // if (clonedGate != null) {
        //   logicGateBar.getChildren().remove(clonedGate);
        //   // System.out.println("cloded gates parents :" + clonedGate.getParent() + "\n");
        //   clonedGate = null;
        //   // thisGate.setAllVisibility(true);
        // } else {
        //   logicGateBar.getChildren().remove(thisGate);
        // }
        // System.out.println("CHildren of logic bar: " + logicGateBar.getChildren());
        // System.out.println("Childrenn of breadboard: " + breadboard.getChildren());
      }
    });
    setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        // mouseAnchorX = e.getX();
        // mouseAnchorY = e.getY();
        thisGate = (LogicGate) e.getSource();
        // creates a new LogicGate if the one that was draggd is the one on the
        // LogicGateBar;
        logicGateBar = getLogicGateBar();
        // if (thisGate.getParent().equals(logicGateBar)) {
          if (breadboard == null) {
            breadboard = getBreadboard();
          }
          // thisGate.setAllVisibility(false);
          try {
            clonedGate = (LogicGate) Class.forName(thisGate.getClass().getName()).getConstructor().newInstance();
            logicGateBar.getChildren().add(clonedGate);
            thisGate.setAllVisibility(false);
            // clonedGate.setAllOpacity(0.5);
          } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
              | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
            e1.printStackTrace();
          }
        // }
      }
    });

    setOnMouseDragged(new EventHandler<MouseEvent>() {
      // plan is to clone this Logic gate and set it invisible, then drag and drop the
      // cloned one to the breadboard

      @Override
      public void handle(MouseEvent e) {
        if (clonedGate != null) {
          // move cloned Gate
          move(clonedGate, e);
        } else {
          // move thisGate
          move(thisGate, e);
        }
        // System.out.println("done dragging");
      }
    });
  }

  private void move(LogicGate gate, MouseEvent e) {
    // double distanceOfLines = getDistanceOfLInes();
    // only temporarily while not calculating the distance of the lines
    if (breadboard == null) {
      this.breadboard = getBreadboard();
    }

    gate.setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
    gate.setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);
    // System.out.println("parent alt: " + gate.getParent());
    if (gate.getLayoutX() > 99) {
      // if needed switches the parents and changes Opacity for visual indication
      if (!gate.getParent().equals(breadboard)) {
        logicGateBar.getChildren().remove(gate);
        breadboard.getChildren().add(gate);
        // gate.setAllOpacity(1);
      }
    } else {
      if (!gate.getParent().equals(logicGateBar)) {
        breadboard.getChildren().remove(gate);
        logicGateBar.getChildren().add(gate);
        // gate.setAllOpacity(0.5);
      }
    }
    // System.out.println("parent neu: " + gate.getParent());
  }

  // for the movement
  // if (getLayoutX() > getParent().getLayoutX() +
  // getParent().getBoundsInLocal().getWidth()
  // && getLayoutY() > getParent().getLayoutY()) {
  // setAllOpacity(1.0);
  // // node.setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX
  // // - (e.getScreenX() - getParent().getLayoutX()) % distanceOfLines);
  // // node.setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY
  // // - (e.getScreenY() - getParent().getLayoutY()) % distanceOfLines);
  // setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
  // setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);
  // } else {
  // setAllOpacity(0.5);
  // setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
  // setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);
  // }

  // public LogicGate(byte inputs) {
  // this();
  // this.inputs = new ArrayList<>(inputs);
  // setPrefSize(WIDTH, 50 * inputs);
  // }

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

  private void setAllOpacity(double opacity) {
    this.setOpacity(opacity);
    for (Node child : this.getChildren()) {
      child.setOpacity(opacity);
    }
  }

  public static int getFutureWidth() {
    return 100;
  }

  private Breadboard getBreadboard() {
    for (Node child : getParent().getParent().getChildrenUnmodifiable()) {
      if (child.getId().equals("breadboard")) {
        return (Breadboard) child;
      }
    }
    return null;
  }

  private LogicGateBar getLogicGateBar() {
    for (Node child : getParent().getParent().getChildrenUnmodifiable()) {
      if (child.getId().equals("logicGateBar")) {
        return (LogicGateBar) child;
      }
    }
    return null;
  }

  private double getDistanceOfLInes() {
    if (breadboard == null) {
      breadboard = getBreadboard();
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
