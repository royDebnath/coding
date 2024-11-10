package com.roydebnath.coding.lld.atm.model;

import lombok.Data;

import java.util.Date;

@Data
public class Card {
  private String cardNumber;
  private String customerName;
  private Date cardExpiry;
  private int pin;
}
