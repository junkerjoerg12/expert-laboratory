package de.junkerjoerg12.expert_laboratory.ui_components;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;

public class ModeBar extends ToolBar {

  public ModeBar(Group root) {
    this.setId("modeBar");

    this.setPrefWidth(1920);
    this.setLayoutY(root.getChildren().get(0).getBoundsInLocal().getMaxY());
    this.setHeight(35);

    this.getItems().add(new Button("connect"));
    this.getItems().add(new Button("choose"));
    this.getItems().add(new ToggleButton("simutating"));
  }
}
