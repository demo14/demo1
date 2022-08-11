package com.example.demo.mappers;

import com.example.demo.dto.AccountDetail;
import com.example.demo.entity.Account;

import java.util.Random;

public class Mapper {

    public static Account toAccount(AccountDetail account) {
        if (account == null) return null;
        var toAccount = new Account();
        toAccount.setName(account.getName());
        toAccount.setLastName(account.getLastName());
        toAccount.setAccountNumber(new Random().nextInt(10 - 5 + 1) + 5);
        return toAccount;
    }

}
