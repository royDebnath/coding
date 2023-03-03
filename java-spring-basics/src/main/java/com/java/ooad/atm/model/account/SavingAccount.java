package com.java.ooad.atm.model.account;


import com.java.ooad.atm.model.account.abstracts.Account;
import lombok.Data;

@Data
public class SavingAccount extends Account {
  private String debitCardNumber;
  private double withdrawLimit;
}
