package org.example.model.states;

import org.example.model.VendingMachine;

public class NoMoneyInsertedState implements VendingMachineState {

  private static VendingMachineState INSTANCE = new NoMoneyInsertedState();
  public static VendingMachineState getInstance(){
    return INSTANCE;
  }

  @Override
  public void insertMoney(VendingMachine machine, int amount) {
    machine.addCustomerMoney(amount);
    machine.setState(MoneyInsertedState.getInstance());
    System.out.println("Vending machine has beeen added with : " + amount);
  }

  @Override
  public void selectProduct(VendingMachine machine, int rackId, int productId) {
    System.out.println("No money added! Cannot add product");
  }

  @Override
  public void dispenseProduct(VendingMachine machine) {
    System.out.println("No money added! Cannot dipense");
  }

  @Override
  public void refundMoney(VendingMachine machine) {
    System.out.println("No money added! refund");
  }
}
