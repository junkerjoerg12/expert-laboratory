package de.junkerjoerg12.expert_laboratory.ui_components;

import de.junkerjoerg12.expert_laboratory.logicGates.LogicGate;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Connection extends Line {
  protected LogicGate input;
  protected LogicGate output;

  protected boolean condition;


  public Connection() {
    setStyle("-fx-stroke: grey");
    setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        System.out.println("condition: " + condition);
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
      }
    });
  }

  public Connection(double startX, double startY) {
    this();
    setStartX(startX);
    setEndX(startX);
    setStartY(startY);
    setEndY(startY);
  }


  public Connection(double startX, double starY, double endX, double endY) {
    this();
    setStartX(startX);
    setEndX(endX);
    setStartY(startX);
    setEndY(endY);
  }
}
