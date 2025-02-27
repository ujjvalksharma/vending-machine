package org.example.model;

import org.example.model.states.NoMoneyInsertedState;
import org.example.model.states.VendingMachineState;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * We are using state design pattern. based on the state of object, we perform behaviours.
 * Initially vending machine in NoMoneyState
 * In NoMoneyState :
 * it can only accept money (with state change to MoneyInserted)
 * and rest no operations
 *
 * In MoneyInsertedState :
 * it can accept money (with no state change ) and
 * refund (with state change to NoMoneyState) and
 * select product (with state change to dispense),
 * but does not dispense
 *
 * In DispensingState :
 * It can only dispense (with state change to NoMoneyState)  and rest of operations cannot be performed
 *
 *
 *
 */
@Data
public class VendingMachine {
  private static VendingMachine vendingMachine = new VendingMachine();
  private List<Rack> racks;
  private int customerMoney;
  private Product selectedProduct;

  VendingMachineState vendingMachineState;
  private VendingMachine(){
    this.racks = new ArrayList<>();
    this.customerMoney = 0;
    this.vendingMachineState = NoMoneyInsertedState.getInstance();
  }



  public static VendingMachine getInstance() {
    return vendingMachine;
  }

  public void insertMoney(int amount) {
    vendingMachineState.insertMoney(this, amount);
  }

  public void selectProduct(int rackId, int productId) {
    vendingMachineState.selectProduct(this, rackId, productId);
  }

  public void dispenseProduct() {
    vendingMachineState.dispenseProduct(this);
  }

  public void refundMoney() {
    vendingMachineState.dispenseProduct(this);
  }

  public void addCustomerMoney(int money) {
    customerMoney += money;
  }


  public void setState(VendingMachineState state) {
    this.vendingMachineState = state;
  }

  public boolean isProductAvailable(int rackId, int productId) {
    return racks.stream()
            .filter(r -> r.getId() == rackId)
            .flatMap(r -> r.getProducts().stream())
            .filter(p -> p.getId() == productId)
            .count() > 0;
  }

  public Product getProduct(int rackId, int productId) {
    return racks.stream()
            .filter(r -> r.getId() == rackId)
            .flatMap(r -> r.getProducts().stream())
            .filter(p -> p.getId() == productId)
            .findFirst()
            .get();
  }

}
