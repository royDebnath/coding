package com.roydebnath.coding.lld.atm.model.transaction;


import com.roydebnath.coding.lld.atm.model.transaction.abstracts.Transaction;

public class Withdraw extends Transaction {
  private double amount;

  @Override
  public boolean makeTransation() {
    return false;
  }
}