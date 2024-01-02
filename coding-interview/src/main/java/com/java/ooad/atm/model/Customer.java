package com.java.ooad.atm.model;

import com.java.ooad.atm.model.account.abstracts.Account;
import com.java.ooad.atm.model.enumerations.CustomerStatus;
import lombok.Data;

@Data
public class Customer {
  private String name;
  private String email;
  private String phone;
  private CustomerStatus status;
  private Card card;
  private Account account;

}