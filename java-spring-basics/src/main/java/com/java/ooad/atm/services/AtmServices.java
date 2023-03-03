package com.java.ooad.atm.services;


import com.java.ooad.atm.model.Card;
import com.java.ooad.atm.model.account.abstracts.Account;

public class AtmServices {

    BankServices bankServices;

    public boolean authenticateCustomer(Card card){
        return bankServices.validateCard(card.getCardNumber(), card.getCardExpiry());
    };

    public long showBalance(Account account){
        return account.getAvailableBalance();
    }

}
