package org.example.model.states;

import org.example.model.Product;
import org.example.model.Rack;
import org.example.model.VendingMachine;

public class DispensingState implements VendingMachineState {
  private static VendingMachineState INSTANCE = new DispensingState();
  public static VendingMachineState getInstance(){
    return INSTANCE;
  }
  @Override
  public void insertMoney(VendingMachine machine, int amount) {
    System.out.println("Cannot insert more money due in dispensing state");
  }

  @Override
  public void selectProduct(VendingMachine machine, int rackId, int productId) {
    System.out.println("Cannot select product due in dispensing state");
  }

  @Override
  public void dispenseProduct(VendingMachine machine) {
    Product product = machine.getSelectedProduct();
    System.out.println("Dispending product: " + product);
    Rack rack = machine
            .getRacks()
            .stream()
            .filter(r -> r.getProducts().contains(product))
            .findFirst()
            .get();
    rack.getProducts().remove(product);
    machine.setSelectedProduct(null);
    machine.setCustomerMoney(0);
    machine.setState(NoMoneyInsertedState.getInstance());
  }

  @Override
  public void refundMoney(VendingMachine machine) {
    System.out.println("Cannot refund while dispensing!");
  }
}
