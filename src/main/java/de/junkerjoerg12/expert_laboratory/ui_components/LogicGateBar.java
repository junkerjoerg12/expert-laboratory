package de.junkerjoerg12.expert_laboratory.ui_components;

import de.junkerjoerg12.expert_laboratory.logicGates.LogicGate;
import de.junkerjoerg12.expert_laboratory.logicGates.Nand;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class LogicGateBar extends Pane {

  private Breadboard breadboard;

  public LogicGateBar(Group root) {
    setId("logicGateBar");
    
    breadboard = (Breadboard) root.getChildren().get(2);
    setLayoutX(0);
    setLayoutY(root.getChildren().get(1).getBoundsInLocal().getMaxY()
        + root.getChildren().get(0).getBoundsInLocal().getMaxY());
    setPrefHeight(1080 - getBoundsInLocal().getMinY());
    setPrefWidth(LogicGate.getFutureWidth());
    // make it green
    setStyle("-fx-background-color: green;");

  }

  public void addLogicGates() {
    getChildren().add(new Nand(breadboard));
  }
}
