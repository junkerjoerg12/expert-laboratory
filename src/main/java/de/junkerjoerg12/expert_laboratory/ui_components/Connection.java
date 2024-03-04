package de.junkerjoerg12.expert_laboratory.ui_components;

import de.junkerjoerg12.expert_laboratory.logicGates.LogicGate;
import javafx.scene.shape.Line;

public class Connection extends Line {
  protected LogicGate input;
  protected LogicGate output;

  protected boolean condition;


  
  public Connection(double startX, double startY) {
    setStartX(startX);	
    setEndX(startX);
    setStartY(startY);
    setEndY(startY);
  }
}
