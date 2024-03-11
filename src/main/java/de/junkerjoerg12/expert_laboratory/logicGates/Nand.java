package de.junkerjoerg12.expert_laboratory.logicGates;

import de.junkerjoerg12.expert_laboratory.ui_components.Breadboard;

public class Nand extends LogicGate {

  private static int nandcounter = 0;
  // public Nand(byte inputs, Breadboard breadboard) {
  //   // super(inputs);
  // }

  public Nand(Breadboard breadboard) {
    super(breadboard);
    setId("nand " + nandcounter);
    System.out.println("der Nandcounter ist: " + nandcounter);
    nandcounter++;
  }
  // @Override
  public boolean getOutput() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOutput'");
  }

}
