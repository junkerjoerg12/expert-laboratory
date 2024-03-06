package de.junkerjoerg12.expert_laboratory.logicGates;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.junkerjoerg12.expert_laboratory.ui_components.Breadboard;
import de.junkerjoerg12.expert_laboratory.ui_components.Connection;
import de.junkerjoerg12.expert_laboratory.ui_components.LogicGateBar;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public abstract class LogicGate extends Pane {
  // in case the hierarchy is messed up change to StackPane
  protected ArrayList<Boolean> inputs;
  protected boolean output;

  protected final double WIDTH = 100;

  protected static double distanceOfLines = 10;

  protected double mouseAnchorX;
  protected double mouseAnchorY;

  protected Breadboard breadboard;
  protected LogicGateBar logicGateBar;

  protected Rectangle rect;

  protected LogicGate clonedGate;
  protected LogicGate thisGate;

  public LogicGate() {
    this(2);
  }

  public LogicGate(int numberOfInputs) {
    this.inputs = new ArrayList<>(numberOfInputs);
    for (int i = 0; i < 2; i++) {
      inputs.add(false);
    }
    // this.setStyle("-fx-background-color: pink;");
    setPrefSize(WIDTH, 100);
    addRectangel();
    addConnection();

    setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        // creates a new LogicGate object
        mouseAnchorX = e.getX();
        mouseAnchorY = e.getY();
        thisGate = (LogicGate) e.getSource();
        logicGateBar = getLogicGateBar();
        if (breadboard == null) {
          breadboard = getBreadboard();
        }

        // got to set the cloned gate Visible at some point right here
        try {
          clonedGate = (LogicGate) Class.forName(thisGate.getClass().getName()).getConstructor().newInstance();
          logicGateBar.getChildren().add(clonedGate);
          thisGate.setAllVisibility(false);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
          e1.printStackTrace();
        }
        // }
      }
    });

    setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        // moves the new LogicGate object
        if (clonedGate != null) {
          // move cloned Gate
          move(clonedGate, e);
        } else {
          // move thisGate
          move(thisGate, e);
        }
      }
    });

    setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        // deletes the not needed objects
        thisGate.setAllVisibility(true);
        if (thisGate.getParent().getId().equals("breadboard")) {
          breadboard.getChildren().remove(thisGate);
        }
        if (clonedGate.getParent().getId().equals("logicGateBar")) {
          logicGateBar.getChildren().remove(clonedGate);
          clonedGate = null;
        }
      }
    });
  }

  private void addConnection() {
    for (int i = 1; i <= inputs.size(); i++) {
      System.out.println("in the for loop");
      // Connection line = new Connection(rect.getX() - rect.getWidth() / 2, i * 20,
      // rect.getX() -10, i * 20);
      Connection line = new Connection(0, 0, 20, 0);
      line.setLayoutY(i * ((100 / (inputs.size() + 1)) - (100 / (inputs.size() + 1)) % LogicGate.distanceOfLines));
      System.out.println(100 / (inputs.size() + 1));
      getChildren().add(line);

    }
    System.out.println(getChildren());
  }

  private void move(LogicGate gate, MouseEvent e) {
    double distanceOfLines = getDistanceOfLInes();
    if (breadboard == null) {
      this.breadboard = getBreadboard();
    }
    if (gate.getLayoutX() > 99 && gate.getLayoutY() > 0) {
      // if needed switches the parents and changes Opacity for visual indication
      gate.setLayoutX(
          e.getScreenX() - getParent().getLayoutX() - mouseAnchorX - (e.getScreenX() - mouseAnchorX) % distanceOfLines);
      gate.setLayoutY(
          e.getScreenY() - getParent().getLayoutY() - mouseAnchorY - (e.getScreenY() - mouseAnchorY) % distanceOfLines);
      if (!gate.getParent().equals(breadboard)) {
        logicGateBar.getChildren().remove(gate);
        breadboard.getChildren().add(gate);
        gate.setAllOpacity(1);
      }
    } else {
      gate.setLayoutX(e.getSceneX() - getParent().getLayoutX() - mouseAnchorX);
      gate.setLayoutY(e.getSceneY() - getParent().getLayoutY() - mouseAnchorY);
      if (!gate.getParent().equals(logicGateBar)) {
        breadboard.getChildren().remove(gate);
        logicGateBar.getChildren().add(gate);
        gate.setAllOpacity(0.5);
      }
    }
  }

  private void addRectangel() {
    Rectangle rect = new Rectangle();
    this.rect = rect;
    // calc the Height and width in the Futur
    rect.setWidth(100 - 4 * LogicGate.distanceOfLines);
    rect.setHeight(WIDTH);
    // place the rectangle in the center of the pane
    rect.setLayoutX(getPrefWidth() / 2 - rect.getWidth() / 2);
    rect.setLayoutY(getPrefHeight() / 2 - rect.getHeight() / 2);
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
        System.out.println(breadboard.getChildrenUnmodifiable());
        System.err.println("look over the hieraarchy of the children from breadboard");
      }
    }
    return -1;
  }
}
