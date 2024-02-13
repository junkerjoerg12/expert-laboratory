package de.junkerjoerg12.expert_laboratory;

import de.junkerjoerg12.expert_laboratory.logicGates.LogicGate;
import de.junkerjoerg12.expert_laboratory.logicGates.Nand;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class LogicGateBar extends Pane {
  public LogicGateBar(Group root) {
    this.setId("logicGateBar");

    this.setLayoutX(0);
    this.setLayoutY(root.getChildren().get(1).getBoundsInLocal().getMaxY() + root.getChildren().get(0).getBoundsInLocal().getMaxY());
    this.setPrefHeight(1080 - getBoundsInLocal().getMinY());
    this.setPrefWidth(LogicGate.getFutureWidth());
    // make it green
    this.setStyle("-fx-background-color: green;");

    this.getChildren().add(new Nand());
  }
}
