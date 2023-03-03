package com.java.ooad.atm.model.transaction.abstracts;

import com.java.ooad.atm.model.enumerations.TransactionStatus;
import lombok.Data;

import java.util.Date;

@Data
public abstract class Transaction {
  private int transactionId;
  private Date creationTime;
  private TransactionStatus status;

  public abstract boolean makeTransation();
}