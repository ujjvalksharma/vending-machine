package org.example.model.states;

import org.example.model.VendingMachine;

public interface VendingMachineState {
  void insertMoney(VendingMachine machine, int amount);
  void selectProduct(VendingMachine machine, int rackId, int productId);
  void dispenseProduct(VendingMachine machine);
  void refundMoney(VendingMachine machine);
}
