package de.junkerjoerg12.expert_laboratory.ui_components;

import de.junkerjoerg12.expert_laboratory.logicGates.LogicGate;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Breadboard extends Pane {
  private int gridDistance;

  public Breadboard(Group root, int gridDistance) {
    this.setId("breadboard");
    this.gridDistance = gridDistance;
    this.setLayoutX(LogicGate.getFutureWidth());
    this.setLayoutY(root.getChildren().get(1).getBoundsInLocal().getMaxY()
        + root.getChildren().get(0).getBoundsInLocal().getMaxY());
    this.setWidth(1920 - LogicGate.getFutureWidth());
    this.setHeight(1000);
    // make the color of the breadboard white
    this.setStyle("-fx-background-color: white;");
    paintGrid(gridDistance);
  }

  private void paintGrid(int distanceOfLines) {
    double verticalLines = getWidth() / gridDistance;
    double horizontalLines = getHeight() / gridDistance;

    for (int i = 0; i < horizontalLines; i++) {
      Line line = new Line(0, i * gridDistance, getWidth(), i * gridDistance);
      line.setOpacity(0.2);
      getChildren().add(line);
    }

    for (int i = 0; i < verticalLines; i++) {
      Line line = new Line(i * gridDistance, 0, i * gridDistance, getHeight());
      line.setOpacity(0.2);
      getChildren().add(line);
    }

  }
}
