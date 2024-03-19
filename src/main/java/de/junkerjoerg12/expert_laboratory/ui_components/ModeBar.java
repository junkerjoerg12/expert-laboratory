package de.junkerjoerg12.expert_laboratory.ui_components;

import javax.crypto.AEADBadTagException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;

public class ModeBar extends ToolBar {
  private Breadboard breadboard;

  public ModeBar(Group root) {
    this.setId("modeBar");

    this.setPrefWidth(1920);
    this.setLayoutY(root.getChildren().get(0).getBoundsInLocal().getMaxY());
    this.setHeight(35);

    Button b = new Button("connect");
    b.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        buttonPressed(e);
      }
    });
    this.getItems().add(b);

    b = new Button("choose");
    b.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        buttonPressed(e);
      }
    });
    this.getItems().add(b);

    b = new Button("simulate");
    b.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        buttonPressed(e);
      }
    });
    this.getItems().add(b);
  }

  public void buttonPressed(ActionEvent e) {
    System.out.println("button was pressed");
    if (e.getSource() == getItems().get(0)) {
      System.out.println("connecting");
      breadboard.setMode("connecting");
    } else if (e.getSource() ==getItems().get(1)) {
      breadboard.setMode("normal");
      System.out.println("normal");
    }
  }

  public void setBreadboard(Breadboard b) {
    breadboard = b;
  }

}
