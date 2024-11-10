package com.roydebnath.coding.lld.atm.services;

import com.roydebnath.coding.lld.atm.model.Card;
import com.roydebnath.coding.lld.atm.model.account.abstracts.Account;

public class AtmServices {

    BankServices bankServices;

    public boolean authenticateCustomer(Card card){
        return bankServices.validateCard(card.getCardNumber(), card.getCardExpiry());
    };

    public long showBalance(Account account){
        return account.getAvailableBalance();
    }

}
