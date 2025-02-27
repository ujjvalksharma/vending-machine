package org.example.model.states;

import org.example.model.Product;
import org.example.model.VendingMachine;

public class MoneyInsertedState implements VendingMachineState {
  private static VendingMachineState INSTANCE = new MoneyInsertedState();
  public static VendingMachineState getInstance(){
    return INSTANCE;
  }

  @Override
  public void insertMoney(VendingMachine machine, int amount) {
    machine.addCustomerMoney(amount);
    System.out.println("Additional Money added : " + amount);
  }

  @Override
  public void selectProduct(VendingMachine machine, int rackId, int productId) {
    if(machine.isProductAvailable(rackId, productId)) {
      Product product = machine.getProduct(rackId, productId);
      if(product.getPrice() > machine.getCustomerMoney()) {
        System.out.println("Insufficient funds selected another product");
      }
      machine.setSelectedProduct(product);
      machine.setState(DispensingState.getInstance());
      System.out.println("Product is selected. Ready to dispense");
    }
  }

  @Override
  public void dispenseProduct(VendingMachine machine) {
    System.out.println("Please select a product");
  }

  @Override
  public void refundMoney(VendingMachine machine) {
    int totalCustomerMoney = machine.getCustomerMoney();
    machine.setCustomerMoney(0);
    System.out.println("Money refunded to customer: " + totalCustomerMoney);
    machine.setState(NoMoneyInsertedState.getInstance());
  }
}
