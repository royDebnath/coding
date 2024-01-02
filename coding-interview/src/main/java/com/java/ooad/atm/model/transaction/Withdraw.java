package com.java.ooad.atm.model.transaction;


import com.java.ooad.atm.model.transaction.abstracts.Transaction;

public class Withdraw extends Transaction {
  private double amount;

  @Override
  public boolean makeTransation() {
    return false;
  }
}