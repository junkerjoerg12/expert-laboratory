package de.junkerjoerg12.expert_laboratory.logicGates;

public class Nand extends LogicGate {

  private static int nandcounter = 0;
  public Nand(byte inputs) {
    super(inputs);
  }

  public Nand() {
    super();
    setId("nand " + nandcounter);
    nandcounter++;
  }
  // @Override
  public boolean getOutput() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOutput'");
  }

}
