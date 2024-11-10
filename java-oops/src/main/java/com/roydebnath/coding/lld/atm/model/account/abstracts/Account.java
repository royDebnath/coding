package com.roydebnath.coding.lld.atm.model.account.abstracts;

import lombok.Data;

@Data
public class Account {
  private int accountNumber;
  private double totalBalance;
  private long availableBalance;
}