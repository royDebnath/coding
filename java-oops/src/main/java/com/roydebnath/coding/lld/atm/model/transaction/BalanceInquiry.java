package com.roydebnath.coding.lld.atm.model.transaction;

import com.roydebnath.coding.lld.atm.model.transaction.abstracts.Transaction;
import lombok.Data;

@Data
public class BalanceInquiry extends Transaction {
  private int accountId;

  @Override
  public boolean makeTransation() {
    return false;
  }
}