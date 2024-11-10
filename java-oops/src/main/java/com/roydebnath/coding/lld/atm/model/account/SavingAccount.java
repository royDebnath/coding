package com.roydebnath.coding.lld.atm.model.account;


import com.roydebnath.coding.lld.atm.model.account.abstracts.Account;
import lombok.Data;

@Data
public class SavingAccount extends Account {
  private String debitCardNumber;
  private double withdrawLimit;
}
