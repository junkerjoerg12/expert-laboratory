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
import javafx.scene.shape.Rectangle;

public abstract class LogicGate extends Pane {
  // in case the hierarchy is messed up change to StackPane
  protected ArrayList<Boolean> inputs;
  protected ArrayList<Connection> inputConnections;

  protected boolean output;
  protected Connection outputConnection;

  protected final double WIDTH = 100;

  protected static double distanceOfLines = 10;

  protected double mouseAnchorX;
  protected double mouseAnchorY;

  protected Breadboard breadboard;
  protected LogicGateBar logicGateBar;

  protected Rectangle rect;

  protected LogicGate clonedGate;
  protected LogicGate thisGate;

  public LogicGate(Breadboard breadboard) {
    this(2, breadboard);
  }

  public LogicGate(int numberOfInputs, Breadboard breadboard) {
    this.breadboard = breadboard;
    this.inputs = new ArrayList<>(numberOfInputs);
    this.inputConnections = new ArrayList<>(numberOfInputs);
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
        // if (breadboard == null) {
        // breadboard = getBreadboard();
        // }

        // got to set the cloned gate Visible at some point right here
        try {
          System.out.println("New Gate");
          System.out.println(breadboard);
          clonedGate = (LogicGate) Class.forName(thisGate.getClass().getName()).getConstructor(Breadboard.class)
              .newInstance(breadboard);
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
          System.out.println("parent of cloned gate: " + clonedGate.getParent());
          logicGateBar.getChildren().remove(clonedGate);
          clonedGate = null;
        } else {
          System.out.println("gedropptes gate:");
          for (Connection c : clonedGate.inputConnections) {
            // System.out.println(c);
            c.setPoints();
          }
          clonedGate.outputConnection.setPoints();
        }
      }
    });
  }

  private void addConnection() {
    // the input connections
    System.out.println("Die Connections von einem neuen LogicGate");
    for (int i = 1; i <= inputs.size(); i++) {
      Connection line = new Connection(0, 0, 2 * distanceOfLines, 0, breadboard);
      line.setLayoutY(i * ((getBoundsInLocal().getHeight() / (inputs.size() + 1))
          - (getBoundsInLocal().getHeight() / (inputs.size() + 1)) % distanceOfLines));
      System.out.println();
      getChildren().add(line);
      inputConnections.add(line);
      line.calculateAbsolutePositions();
      // System.out.println(line);
    }
    // the output connection
    Connection line = new Connection(0, 0, 2 * distanceOfLines, 0, breadboard);
    line.setLayoutX(getBoundsInLocal().getWidth());
    line.setLayoutY(getBoundsInLocal().getHeight() / 2 - (getBoundsInLocal().getHeight() / 2) % distanceOfLines);
    getChildren().add(line);
    outputConnection = line;
    line.calculateAbsolutePositions();
    // System.out.println(line);
  }

  private void move(LogicGate gate, MouseEvent e) {
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
    rect.setWidth(100 - 4 * distanceOfLines);
    rect.setHeight(WIDTH);
    // place the rectangle in the center of the pane
    rect.setLayoutX(getPrefWidth() / 2 - rect.getWidth() / 2);
    rect.setLayoutY(getPrefHeight() / 2 - rect.getHeight() / 2);
    getChildren().add(rect);
  }

  public void setAllVisibility(boolean visible) {
    // this.setVisible(visible);
    // for (Node child : this.getChildren()) {
    // child.setVisible(visible);
    // }
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

  // private double getDistanceOfLInes() {
  // if (breadboard == null) {
  // breadboard = getBreadboard();
  // }
  // for (int i = 0; i < breadboard.getChildren().size(); i++) {
  // if (breadboard.getChildren().get(i).getId() == null &&
  // breadboard.getChildren().get(i + 1).getId() == null) {
  // Line line = (Line) breadboard.getChildren().get(i);
  // if (line.getStartX() == line.getEndX()) {
  // Line line1 = (Line) breadboard.getChildren().get(i);
  // Line line2 = (Line) breadboard.getChildren().get(i + 1);
  // return line1.getStartX() - line2.getStartX();
  // } else {
  // Line line1 = (Line) breadboard.getChildren().get(i);
  // Line line2 = (Line) breadboard.getChildren().get(i + 1);
  // return line1.getStartY() - line2.getStartY();
  // }
  // } else {
  // System.out.println(breadboard.getChildrenUnmodifiable());
  // System.err.println("look over the hieraarchy of the children from
  // breadboard");
  // }
  // }
  // return -1;
  // }
}
