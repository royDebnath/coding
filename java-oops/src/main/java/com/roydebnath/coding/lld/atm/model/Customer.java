package com.roydebnath.coding.lld.atm.model;

import com.roydebnath.coding.lld.atm.model.account.abstracts.Account;
import com.roydebnath.coding.lld.atm.model.enumerations.CustomerStatus;
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