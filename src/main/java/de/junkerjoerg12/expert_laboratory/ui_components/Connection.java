package de.junkerjoerg12.expert_laboratory.ui_components;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Connection extends Line {

  protected Connection input;
  private Connection output;

  protected Breadboard breadboard;

  // the absolute values of the starting and end Point of the Connection, i don't
  // know if relative to the Screnn or th eBreadbpard but that shouldt matter
  protected double absoluteStartX;
  protected double absoluteStartY;
  protected double absoluteEndX;
  protected double absoluteEndY;

  protected Point2D start;
  protected Point2D end;

  protected Boolean condition = false;
  protected Text showCondition;

  public Connection(Breadboard breadboard) {
    setStyle("-fx-stroke: purple");
    setStrokeWidth(3);
    this.breadboard = breadboard;

    showCondition = new Text(200, 200, condition.toString());
    breadboard.getChildren().add(showCondition);
    showCondition.setVisible(false);
    setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (getParent() == breadboard || getParent().getParent() == breadboard) {
          showInformation(true, e);
        }
      }
    });
    setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        showInformation(false, e);
      }
    });

  }

  protected void showInformation(boolean show, MouseEvent e) {
    if (show) {
      showCondition.setX(e.getScreenX() - breadboard.getLayoutX());
      showCondition.setY(e.getScreenY() - breadboard.getLayoutY() - 30);
    }
    showCondition.setVisible(show);
  }

  public Connection(double startX, double startY, Breadboard breadboard) {
    this(breadboard);
    setStartX(startX);
    setEndX(startX);
    setStartY(startY);
    setEndY(startY);
    start = new Point2D(startX, startY);
    // draws a circle for visual controll
    breadboard.getChildren().add(new Circle(start.getX(), start.getY(), 4, javafx.scene.paint.Color.BLACK));
  }

  public Connection(double startX, double starY, double endX, double endY, Breadboard breadboard) {
    this(breadboard);
    setStartX(startX);
    setEndX(endX);
    setStartY(startX);
    setEndY(endY);
  }

  // this function should only be called if getParet(). getClass() extends
  // LogicGate
  public void setPoints() {
    start = new Point2D(getParent().getLayoutX() + getLayoutX() + getStartX(),
        getParent().getLayoutY() + getLayoutY() + getStartY());

    // draws a circle for visual controll
    breadboard.getChildren().add(new Circle(start.getX(), start.getY(), 4,
        javafx.scene.paint.Color.BLACK));

    end = new Point2D(getParent().getLayoutX() + getLayoutX() + getEndX(),
        getParent().getLayoutY() + getLayoutY() + getEndY());
    breadboard.getChildren().add(new Circle(end.getX(), end.getY(), 4,
        javafx.scene.paint.Color.BLACK));
  }

  public void setEndPoint() {
    end = new Point2D(getEndX(), getEndY());
    // draws a circle for visual controll
  }

  public void printPosition() {
    System.out.println("Position of " + this);
  }

  public void connect() {
    System.out.println("calling the connect methode");
    ArrayList<Connection> connections =  breadboard.getConnections();
    for (Connection c : connections) {
      if (c.getStart().equals(start)) {
        input = c;
        c.setInputConnection(this);
        drawCircle(start);
      } else if (c.getStart().equals(end)) {
        output = c;
        drawCircle(end);
        c.setInputConnection(this);
      } else if (c.getEnd().equals(start)) {
        input = c;
        drawCircle(start);
        c.setOutputConnection(this);
      } else if (c.getEnd().equals(end)) {
        output = c;
        c.setOutputConnection(this);
        drawCircle(end);
      }
      System.out.println("input: " + c.input + " output: " + c.output);
    }
    System.out.println("input: " + input + " output: " + output);
  }

  private void drawCircle(Point2D p){
    breadboard.getChildren().add(new Circle(p.getX(), p.getY(), 4, javafx.scene.paint.Color.BLACK));
  }
  public Connection getInoutConnection() {
    return input;
  }

  public void setInputConnection(Connection c) {
    input = c;
  }

  public Connection getOutputConnection() {
    return output;
  }

  public void setOutputConnection(Connection c) {
    output = c;
  }

  public Point2D getStart() {
    return start;
  }

  public Point2D getEnd() {
    return end;
  }
}
