package de.junkerjoerg12.expert_laboratory.ui_components;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Breadboard extends Pane {
  private boolean connecting = true;
  private boolean normal;

  private Connection selectedConnection;
  private double mouseAnchorX;
  private double mouseAnchorY;

  private int gridDistance;

  public Breadboard(Group root, int gridDistance) {
    this.setId("breadboard");
    this.gridDistance = gridDistance;
    this.setLayoutX(000);
    this.setLayoutY(root.getChildren().get(1).getBoundsInLocal().getMaxY()
        + root.getChildren().get(0).getBoundsInLocal().getMaxY());
    this.setPrefWidth(1920);
    this.setPrefHeight(1000);
    // make the color of the breadboard white
    this.setStyle("-fx-background-color: white;");
    paintGrid(gridDistance);

    setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (connecting) {
          // create new connection
          selectedConnection= new Connection(e.getX() - e.getX() % gridDistance, e.getY() - e.getY( )% gridDistance);
          selectedConnection.setStyle("-fx-stroke: grey");
          getChildren().add(selectedConnection);
          mouseAnchorY = e.getY() - e.getY() % gridDistance;
          mouseAnchorX = e.getX() - e.getX() % gridDistance;
        }
      }
    });

    setOnMouseDragged(new EventHandler<MouseEvent>() {
      // to maybe set the Coursor to the exact position where the Connection starts:
      //https://stackoverflow.com/questions/2941324/how-do-i-set-the-position-of-the-mouse-in-java

      @Override
      public void handle(MouseEvent e) {
        // selectedConnection
        if (connecting) {
          System.out.println("MouseAncherX = " + mouseAnchorX + " MouseanchorY = " + mouseAnchorY);
          
          System.out.println(" X : " + Math.abs(mouseAnchorX - e.getX()) + ", Y : " + Math.abs(mouseAnchorY - e.getY()));

          if (Math.abs(mouseAnchorY - e.getY()) > Math.abs(mouseAnchorX - e.getX())) {
            System.out.println(" Y > X ");
            selectedConnection.setEndY(e.getY() - e.getY() % gridDistance);
            selectedConnection.setEndX(mouseAnchorX);
          } else {
            System.out.println(" X > Y ");
            selectedConnection.setEndX(e.getX() - e.getX() % gridDistance);
            selectedConnection.setEndY(mouseAnchorY);
          }
        }
      }
    });
  

    setOnMouseReleased(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent e) {
        if (connecting) {
          if (e.getX() - e.getX() % gridDistance == mouseAnchorX
              && e.getY() - e.getY() % gridDistance == mouseAnchorY) {
            getChildren().remove(selectedConnection);
          }
          selectedConnection.setStyle("-fx-stroke: black");
          selectedConnection = null;
          System.out.println(" \n");
          mouseAnchorX = 0;
          mouseAnchorY = 0;
        }
      }
      
    });
  }

  private void paintGrid(int distanceOfLines) {
    double verticalLines = getPrefWidth() / gridDistance;
    double horizontalLines = getPrefHeight() / gridDistance;
    for (int i = 0; i < horizontalLines; i++) {
      Line line = new Line(0, i * gridDistance, getPrefWidth(), i * gridDistance);
      line.setOpacity(0.2);
      getChildren().add(line);
    }
    for (int i = 0; i < verticalLines; i++) {
      Line line = new Line(i * gridDistance, 0, i * gridDistance, getPrefHeight());
      line.setOpacity(0.2);
      getChildren().add(line);
    }
  }
}
