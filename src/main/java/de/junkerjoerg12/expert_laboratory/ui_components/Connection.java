package de.junkerjoerg12.expert_laboratory.ui_components;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Connection extends Line {

  protected Connection input;
  protected Connection output;

  protected Breadboard breadboard;

  // the absolute values of the starting and end Point of the Connection, i don't
  // know if relative to the Screnn or th eBreadbpard but that shouldt matter
  protected double absoluteStartX;
  protected double absoluteStartY;
  protected double absoluteEndX;
  protected double absoluteEndY;

  protected Point2D start;
  protected Point2D end;

  protected boolean condition;

  public Connection(Breadboard breadboard) {
    setStyle("-fx-stroke: purple");
    if (breadboard == null) {
      System.out.println("breadbord is null");
    } else {
      this.breadboard = breadboard;
    }
  }

  public Connection(double startX, double startY, Breadboard breadboard) {
    this(breadboard);
    setStartX(startX);
    setEndX(startX);
    setStartY(startY);
    setEndY(startY);
    start = new Point2D(startX, startY);
    breadboard.getChildren().add(new Circle(start.getX(), start.getY(), 4, javafx.scene.paint.Color.BLACK));
  }

  public Connection(double startX, double starY, double endX, double endY, Breadboard breadboard) {
    this(breadboard);
    setStartX(startX);
    setEndX(endX);
    setStartY(startX);
    setEndY(endY);
  }

  public void setEndPoint() {
    end = new Point2D(getEndX(), getEndY());
    breadboard.getChildren().add(new Circle(end.getX(), end.getY(), 4, javafx.scene.paint.Color.BLACK));
  }

  public boolean checkTouching(Connection checkWith) {
    Shape intersection = intersect(checkWith, this);
    if (intersection.getBoundsInLocal().getWidth() != -1) {
      System.out.println("the lines intersect");
      return true;
    } else{
      System.out.println("the lines do not intersect");
      return false;
    }
  }

  public void printPosition() {
    System.out.println("Position of " + this);
  }

  public void calculateAbsolutePositions() {
    // Node parent = (Node) getParent();

    // double length = getEndX() - getStartX();
    // double height = getEndY() - getStartY();

    // absoluteStartX = parent.getLayoutX() + getStartX();
    // absoluteStartY = parent.getLayoutY() + getStartY();
    // absoluteEndX = parent.getLayoutX() + getEndX() + length;
    // absoluteEndY = parent.getLayoutY() + getEndY() + height;
    // System.out.println("parents layout: " + parent.getLayoutX() + " " +
    // parent.getLayoutY());
    // System.out.println("connection layout X: " + getLayoutX() + ", Y: " +
    // getLayoutY());
    // System.out.println(this);

    // System.out.println("Absolute koordinaten: sX: " + absoluteStartX + ", sY: " +
    // absoluteStartY + ", eX: " + absoluteEndX + ", eY: " + absoluteEndY);
    // System.out.println();
  }
}
